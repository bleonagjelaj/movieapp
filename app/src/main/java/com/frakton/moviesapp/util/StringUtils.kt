package com.frakton.moviesapp.util

import android.text.SpannableStringBuilder
import androidx.core.text.bold

val String.Companion.EMPTY: String
    get() = ""

val String.Companion.TWO_CHAR_SPACE: String
    get() = "  "

val String.Companion.NEW_LINE: String
    get() = "\n"

fun String.makeTextBold() = SpannableStringBuilder().bold { append(this@makeTextBold) }