package com.bleonahasanaj.moviesapp.util

import android.widget.ImageView
import com.bleonahasanaj.moviesapp.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

fun ImageView.loadImage(imagePath: String) {
    Picasso.get().load(imagePath)
        .error(R.drawable.ic_image_not_supported)
        .into(this)
}

fun ImageView.loadAndFitImage(imagePath: String) {
    Picasso.get().load(imagePath)
        .error(R.drawable.ic_image_not_supported)
        .fit()
        .into(this)
}

fun ImageView.loadAndTransformImage(imagePath: String, transformationsList: List<Transformation>) {
    Picasso.get().load(imagePath)
        .transform(transformationsList)
        .into(this)
}