package com.bleonahasanaj.moviesapp.data.retrofit.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailsResponse(
    @Json(name = "id")
    val movieId: Long,
    val adult: Boolean?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "belongs_to_collection")
    val belongsToCollection: Any? = null,
    val budget: Long?,
    val genres: List<Genre>?,
    val homepage: String?,
    @Json(name = "imdb_id")
    val imdbID: String?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany>?,
    @Json(name = "release_date")
    val releaseDate: String?,
    val revenue: Long?,
    val runtime: Long?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    @Json(name = "vote_average")
    val voteAverage: Double?,
    @Json(name = "vote_count")
    val voteCount: Long?
)

@JsonClass(generateAdapter = true)
data class Genre(
    val id: Long?,
    val name: String?
)

@JsonClass(generateAdapter = true)
data class ProductionCompany(
    val id: Long?,
    @Json(name = "logo_path")
    val logoPath: String? = null,
    val name: String?,
    @Json(name = "origin_country")
    val originCountry: String?
)
