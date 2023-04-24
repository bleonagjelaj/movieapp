package com.frakton.moviesapp.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Float.roundToOneDecimalPlace(): Float {
    val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))
    return df.format(this).toFloat()
}