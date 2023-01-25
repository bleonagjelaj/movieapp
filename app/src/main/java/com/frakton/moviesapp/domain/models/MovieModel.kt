package com.frakton.moviesapp.domain.models

data class MovieModel(
    val movieId: Long,
    val moviePosterPath: String,
    val movieGenres: String,
    val movieReleaseDate: String,
    val movieRating: Float
)
