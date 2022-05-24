package com.sugaya.apischeduler.utils

import java.text.SimpleDateFormat
import java.util.*

object CalendarUtils {
    private const val PATTERN = "yyyyMM"

    fun getDateLastDay(firstDate: String) : Date {
        val cal = Calendar.getInstance()
        cal.time = convertYearMonthToDate(firstDate)
        cal[Calendar.DAY_OF_MONTH] = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        return cal.time
    }

    fun convertYearMonthToDate(date: String) : Date {
        return SimpleDateFormat(PATTERN).parse(
            if (date.length == 4) (date + "01") else date
        )
    }
}