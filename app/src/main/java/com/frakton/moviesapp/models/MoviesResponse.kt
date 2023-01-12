package com.frakton.moviesapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponse(
    @Json(name = "page")
    val page: Int? = null,
    @Json(name = "results")
    val movies: List<Movies>,
    @Json(name = "total_results")
    val totalResults: Int?,
    @Json(name = "total_pages")
    val totalPages: Int?
)

@JsonClass(generateAdapter = true)
data class Movies(
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "adult")
    val isAdult: Boolean?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "release_date")
    val release_date: String?,
    @Json(name = "genre_ids")
    val genreIds: List<Int>?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "original_title")
    val original_title: String?,
    @Json(name = "original_language")
    val original_language: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "backdrop_path")
    val backdrop_path: String?,
    @Json(name = "popularity")
    val popularity: Int?,
    @Json(name = "vote_count")
    val vote_count: Int?,
    @Json(name = "video")
    val hasVideo: Boolean?,
    @Json(name = "vote_average")
    val vote_average: Int?
)