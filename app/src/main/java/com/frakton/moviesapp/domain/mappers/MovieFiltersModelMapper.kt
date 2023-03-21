package com.frakton.moviesapp.domain.mappers

import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.domain.models.MovieFiltersModel

class MovieFiltersModelMapper {
    fun map(movieFilters: MovieFiltersModel): Filters {
        return Filters(
            sortBy = "${movieFilters.sortBy}.${movieFilters.ordering}",
            filterByYear = getYearFromYearString(movieFilters.filterByYear),
            filterByGenres = getFiltersAsString(movieFilters.filterByGenres)
        )
    }

    private fun getYearFromYearString(filterByYear: String?): Int? {
        return try {
            filterByYear?.toInt()
        } catch (exception: NumberFormatException) {
            null
        }
    }

    private fun getFiltersAsString(filterByGenres: List<Int>?): String? {
        var filtersString = ""
        filterByGenres?.forEachIndexed { index, genre ->
            if (index == 0) {
                filtersString += genre
            } else {
                filtersString += ",$genre"
            }
        }
        return filtersString.ifBlank { null }
    }
}