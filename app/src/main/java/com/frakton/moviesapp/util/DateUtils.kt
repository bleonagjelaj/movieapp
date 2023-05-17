package com.frakton.moviesapp.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.formatDateString(): String {
    val formatter = DateTimeFormatter.ofPattern("MMM yyyy")
    return LocalDate.parse(this).format(formatter)
}

fun String.getYearFromDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy")
    return LocalDate.parse(this).format(formatter)
}