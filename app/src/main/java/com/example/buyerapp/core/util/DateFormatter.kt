package com.example.buyerapp.core.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

fun dateTomorrow() = dateBeforeDays(-1)

fun dateYesterday() = dateBeforeDays(1)

fun dateBeforeWeek() = dateBeforeDays(7)

fun dateBeforeMonth() = dateBeforeMonth(1)

fun dateBefore3Month() = dateBeforeMonth(3)

fun dateBefore6Month() = dateBeforeMonth(6)

fun dateBefore1Year() = dateBeforeMonth(12)


fun dateBeforeDays(days: Int): String {
    val cal = Calendar.getInstance()
    cal.time = Date()
    cal.add(Calendar.DATE, -days)
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(cal.time)
}

fun dateBeforeMonth(months: Int): String {
    val cal = Calendar.getInstance()
    cal.time = Date()
    cal.add(Calendar.MONTH, -months)
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(cal.time)
}
