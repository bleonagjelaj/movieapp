package com.frakton.moviesapp.models

import com.squareup.moshi.Json

data class MoviesResponse(
    @Json(name = "page")
    val page: Int
)
