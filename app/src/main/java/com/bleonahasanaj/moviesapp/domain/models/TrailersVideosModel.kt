package com.bleonahasanaj.moviesapp.domain.models

data class TrailersVideosModel(
    val movieId: Long,
    val trailerVideos: List<TrailerDetails>
)

data class TrailerDetails(
    val key: String,
    val thumbnailPath: String,
    val site: String
)
