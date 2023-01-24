package com.frakton.moviesapp.util

import java.text.SimpleDateFormat

val baseDateFormat = SimpleDateFormat("yyyy-mm-dd")

fun String.formatDateString(): String {
    val parsedDate = baseDateFormat.parse(this)
    return SimpleDateFormat("MMM yyyy").format(parsedDate)
}

fun String.getYearFromDate(): String {
    val parsedDate = baseDateFormat.parse(this)
    return SimpleDateFormat("yyyy").format(parsedDate)
}