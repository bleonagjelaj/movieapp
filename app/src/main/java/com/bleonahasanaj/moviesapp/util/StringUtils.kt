package com.bleonahasanaj.moviesapp.util

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import androidx.core.text.bold

val String.Companion.EMPTY: String
    get() = ""

val String.Companion.TWO_CHAR_SPACE: String
    get() = "  "

val String.Companion.NEW_LINE: String
    get() = "\n"

fun String.makeTextBold() = SpannableStringBuilder().bold { append(this@makeTextBold) }

fun String.makeTextBiggerAndBold(): SpannableStringBuilder {
    val biggerText = SpannableString(this)
    biggerText.setSpan(RelativeSizeSpan(1.3f), 0, this.length, 0)
    return SpannableStringBuilder().bold { append(biggerText) }
}

fun String.makeTextGray(): SpannableStringBuilder {
    val greyText = SpannableString(this)
    greyText.setSpan(
        ForegroundColorSpan(Color.LTGRAY),
        0,
        this.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return SpannableStringBuilder().append(greyText)
}