package com.bleonahasanaj.moviesapp.data.retrofit.models.request

import com.bleonahasanaj.moviesapp.db.tables.Filters

data class GetMoviesRequest(
    val page: Int,
    val movieFilters: Filters? = null
)