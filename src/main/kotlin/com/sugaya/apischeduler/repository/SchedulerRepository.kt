package com.sugaya.apischeduler.repository

import com.sugaya.apischeduler.entity.Schedule
import kotlinx.coroutines.flow.Flow
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SchedulerRepository: CoroutineCrudRepository<Schedule, String> {

    @Query(value = "{date:{ \$gte: ?0, \$lte: ?1}}")
    fun findByDateRange(startDate: Date, endDate: Date) : Flow<Schedule>

    override suspend fun findById(id: String) : Schedule?

    override suspend fun deleteById(id: String)

    override fun findAll() : Flow<Schedule>
}