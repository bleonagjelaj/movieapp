package com.frakton.moviesapp.domain.models

data class MovieTrailerVideosModel(
    val movieId: Long,
    val trailerVideoIds: List<String>
)
