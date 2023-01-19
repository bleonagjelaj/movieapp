package com.frakton.moviesapp.util

import java.text.SimpleDateFormat

fun String.formatDateString(): String {
    val parsedDate = SimpleDateFormat("yyyy-mm-dd").parse(this)
    return SimpleDateFormat("MMM yyyy").format(parsedDate)
}