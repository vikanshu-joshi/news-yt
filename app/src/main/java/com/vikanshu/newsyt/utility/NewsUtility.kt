package com.vikanshu.newsyt.utility

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object NewsUtility {

    fun formatDate(date: String): String {
        return parseDateFormat(date, "yyyy-MM-dd'T'HH:mm:ss'Z'", "MMMM dd, yyyy hh:mm aa") ?: ""
    }

    fun formatSearchNewsDate(date: String): String { // 2022-08-16
        return parseDateFormat(date, "yyyy/MM/dd", "yyyy-MM-dd'T'HH:mm:ss'Z'") ?: ""
    }



    fun getDate(milliSeconds: Long): String {
        val formatter = SimpleDateFormat("yyyy/MM/dd")
        val calendar = Calendar.getInstance();
        calendar.timeInMillis = milliSeconds;
        return formatter.format(calendar.time)
    }



    fun parseDateFormat(
        dateToFormat: String?,
        inputFormat: String?,
        outputFormat: String?
    ): String? {
        val inputFormat = SimpleDateFormat(inputFormat)
        val outputFormat = SimpleDateFormat(outputFormat)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(dateToFormat)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }
}