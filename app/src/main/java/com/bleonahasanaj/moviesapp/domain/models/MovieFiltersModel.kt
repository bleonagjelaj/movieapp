package com.bleonahasanaj.moviesapp.domain.models

data class MovieFiltersModel(
    val sortBy: String? = null,
    val ordering: String? = null,
    val filterByYear: String? = null,
    val filterByGenres: List<Int>?
)
