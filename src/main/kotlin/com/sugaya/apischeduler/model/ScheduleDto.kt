package com.sugaya.apischeduler.model

import com.sugaya.apischeduler.entity.StatusEnum
import java.time.LocalDateTime

data class ScheduleDto (
    val id: String,
    val userId: String,
    val orderNumber: String,
    val status: StatusEnum,
    val type: String,
    val description: String,
    val date: LocalDateTime
)