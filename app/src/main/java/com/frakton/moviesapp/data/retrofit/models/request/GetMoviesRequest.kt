package com.frakton.moviesapp.data.retrofit.models.request

import com.frakton.moviesapp.db.tables.Filters

data class GetMoviesRequest(
    val page: Int,
    val movieFilters: Filters? = null
)