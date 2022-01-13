package com.vikanshu.newsyt.utility

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object NewsUtility {

    fun formatDate(date: String?): String {
        return parseDateFormat(date, "yyyy-MM-dd'T'HH:mm:ss'Z'", "MMMM dd, yyyy hh:mm aa") ?: ""
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