package com.frakton.moviesapp.domain.models

data class MoviesModel(
    val moviePosterPath: String,
    val movieGenres: String,
    val movieReleaseDate: String,
    val movieRating: Float
)
