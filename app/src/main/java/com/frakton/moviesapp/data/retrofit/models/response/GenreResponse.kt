package com.frakton.moviesapp.data.retrofit.models.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreResponse(
    val genres: List<GenreData>
)

data class GenreData(
    val id: Int,
    val name: String
)