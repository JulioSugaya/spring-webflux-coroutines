package com.sugaya.apischeduler.entity

import com.sugaya.apischeduler.model.ScheduleDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document(collection = "schedule")
data class Schedule (
    @Id
    val id: String = UUID.randomUUID().toString(),
    val userId: String,
    val orderNumber: String = orderNumberGen(),
    val status: StatusEnum,
    val type: String,
    val description: String,
    val date: LocalDateTime,
    val createdDate: LocalDateTime = LocalDateTime.now()
){
    fun toDto() : ScheduleDto {
        return ScheduleDto(id, userId, orderNumber, status, type, description, date)
    }
}

private fun orderNumberGen() : String {
    return "1234"
}
