package com.frakton.moviesapp.domain.models

data class MovieDetailsModel(
    val id: Long,
    val title: String,
    val releaseYear: String,
    val posterPath: String,
    val rating: Float,
    val description: String,
    val genres: List<String>,
    val productionCompany: String,
    val budget: String,
    val revenue: String,
    val releaseDate: String
)
