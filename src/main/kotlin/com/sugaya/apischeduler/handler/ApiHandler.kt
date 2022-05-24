package com.sugaya.apischeduler.handler

import com.sugaya.apischeduler.repository.SchedulerRepository
import com.sugaya.apischeduler.entity.Schedule
import com.sugaya.apischeduler.model.ScheduleDto
import com.sugaya.apischeduler.utils.CalendarUtils
import kotlinx.coroutines.flow.map
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class ApiHandler(
    private val schedulerRepository: SchedulerRepository
) {
    companion object {
        private const val ID = "id"
        private const val YEAR_MONTH = "yearmonth"
        private const val START_DATE = "startdate"
        private const val END_DATE = "enddate"
    }

    suspend fun findById(request: ServerRequest): ServerResponse {
        return schedulerRepository.findById(request.pathVariable(ID))
            ?.let {
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(it.toDto())
            } ?: ServerResponse.notFound().buildAndAwait()
    }

    suspend fun findAll(request: ServerRequest): ServerResponse {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(schedulerRepository.findAll().map { it.toDto() })
    }

    suspend fun save(request: ServerRequest): ServerResponse {
        return request.awaitBodyOrNull(Schedule::class)?.let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(schedulerRepository.save(it).toDto())
        } ?: ServerResponse.badRequest().buildAndAwait()
    }

    suspend fun findByYearMonth(request: ServerRequest): ServerResponse {
        val yearMonth = request.pathVariable(YEAR_MONTH)
        return schedulerRepository.findByDateRange(
            CalendarUtils.convertYearMonthToDate(yearMonth),
            CalendarUtils.getDateLastDay(yearMonth)
        ).let {
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .bodyAndAwait(it.map { s -> s.toDto() })
        }
    }

    suspend fun findByDateRange(request: ServerRequest): ServerResponse {
        return schedulerRepository.findByDateRange(
            CalendarUtils.convertYearMonthToDate(request.pathVariable(START_DATE)),
            CalendarUtils.convertYearMonthToDate(request.pathVariable(END_DATE))
        ).let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .bodyAndAwait(it.map { s -> s.toDto() })
        }
    }

    suspend fun delete(request: ServerRequest): ServerResponse {
        return schedulerRepository.findById(request.pathVariable(ID))
            ?.let {
                schedulerRepository.delete(it).let {
                    ServerResponse.ok().buildAndAwait()
                }
            } ?: ServerResponse.notFound().buildAndAwait()
    }
}