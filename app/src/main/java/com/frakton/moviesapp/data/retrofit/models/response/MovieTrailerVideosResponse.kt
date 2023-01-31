package com.frakton.moviesapp.data.retrofit.models.response


data class MovieTrailerVideosResponse(
    val id: Long,
    val results: List<MovieVideoResults>
)

data class MovieVideoResults(
    val name: String,
    val id: String,
    val key: String,
    val site: String
/*     val size: Long,
    val type: String,
    val official: Boolean,
    @Json(name = "published_at")
    val publishedAt: String,*/
)
