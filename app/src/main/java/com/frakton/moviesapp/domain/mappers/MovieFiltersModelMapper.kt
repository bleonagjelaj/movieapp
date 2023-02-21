package com.frakton.moviesapp.domain.mappers

import com.frakton.moviesapp.data.retrofit.models.request.MovieFilters
import com.frakton.moviesapp.db.tables.Filters

class MovieFiltersModelMapper {
    fun map(movieFilters: MovieFilters): Filters {
        return Filters(
            sortBy = movieFilters.sortBy,
            filterByYear = movieFilters.filterByYear?.toString(),
            filterByGenres = movieFilters.filterByGenres
        )
    }
}