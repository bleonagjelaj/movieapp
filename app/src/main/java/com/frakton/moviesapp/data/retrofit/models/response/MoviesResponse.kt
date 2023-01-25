package com.frakton.moviesapp.data.retrofit.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponse(
    val page: Long,
    val results: List<MovieDataModel>,
    @Json(name = "total_results")
    val totalResults: Long,
    @Json(name = "total_pages")
    val totalPages: Long
)

@JsonClass(generateAdapter = true)
data class MovieDataModel(
    @Json(name = "poster_path")
    val posterPath: String? = null,
    val adult: Boolean? = false,
    val overview: String? = null,
    @Json(name = "release_date")
    val releaseDate: String? = null,
    @Json(name = "genre_ids")
    val genreIds: List<Int>? = null,
    val id: Long,
    @Json(name = "original_title")
    val originalTitle: String? = null,
    @Json(name = "original_language")
    val originalLanguage: String? = null,
    val title: String? = null,
    @Json(name = "backdrop_path")
    val backdropPath: String? = null,
    val popularity: Double? = null,
    @Json(name = "vote_count")
    val voteCount: Long? = null,
    val video: Boolean? = false,
    @Json(name = "vote_average")
    val voteAverage: Float? = null
)