package com.example.Bathroom.Util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import org.springframework.stereotype.Component

@Component
class DateTimeConverter {
    @Throws(ParseException::class)
    fun dateTimeConvertFromString(dateTimeString: String): String {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTimeString)
        return SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)
    }

    fun dateTimeConvertFromInstant(instant: Instant?): String {
        if (instant == null) {
            // Handle null case, for example, return an empty string or throw an exception
            return ""
        }
        val zoneId = ZoneId.systemDefault()
        val zonedDateTime = instant.atZone(zoneId)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return formatter.format(zonedDateTime)
    }
}

