package com.frakton.moviesapp.data.retrofit.models.request

data class GetMoviesRequest(
    val page: Int,
    val movieFilters: MovieFilters? = null
)

data class MovieFilters(
    val sortBy: String? = null,
    val filterByYear: Int? = null,
    val filterByGenres: String = ""
)