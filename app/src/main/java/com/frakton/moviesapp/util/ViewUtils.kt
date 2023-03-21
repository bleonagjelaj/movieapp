package com.frakton.moviesapp.util

import android.content.Context
import android.view.View
import android.widget.TextView
import com.frakton.moviesapp.R

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun TextView.toggleOrderingIcon(context: Context) {
    if (this.text == context.getString(R.string.ascending)) {
        this.text = context.getString(R.string.descending)
        this.setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            0,
            R.drawable.ic_descending
        )
    } else {
        this.text = context.getString(R.string.ascending)
        this.setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            0,
            R.drawable.ic_ascending
        )
    }
}