package com.frakton.moviesapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponse(
    @Json(name = "page")
    val page: Int? = null,
    @Json(name = "results")
    val movies: List<Movies>? = null,
    @Json(name = "total_results")
    val totalResults: Int? = null,
    @Json(name = "total_pages")
    val totalPages: Int? = null
)

@JsonClass(generateAdapter = true)
data class Movies(
    @Json(name = "poster_path")
    val posterPath: String? = null,
    @Json(name = "adult")
    val isAdult: Boolean = false,
    @Json(name = "overview")
    val overview: String? = null,
    @Json(name = "release_date")
    val release_date: String? = null,
    @Json(name = "genre_ids")
    val genreIds: List<Int>? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "original_title")
    val original_title: String? = null,
    @Json(name = "original_language")
    val original_language: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "backdrop_path")
    val backdrop_path: String? = null,
    @Json(name = "popularity")
    val popularity: Long? = null,
    @Json(name = "vote_count")
    val vote_count: Int? = null,
    @Json(name = "video")
    val hasVideo: Boolean = false,
    @Json(name = "vote_average")
    val vote_average: Long? = null
)