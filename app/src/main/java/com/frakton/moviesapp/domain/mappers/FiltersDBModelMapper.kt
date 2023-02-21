package com.frakton.moviesapp.domain.mappers

import com.frakton.moviesapp.data.retrofit.models.request.MovieFilters
import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.util.EMPTY

class FiltersDBModelMapper {
    fun map(filters: Filters?): MovieFilters {
        return MovieFilters(
            sortBy = filters?.sortBy,
            filterByYear = getYearFromYearString(filters?.filterByYear),
            filterByGenres = filters?.filterByGenres ?: String.EMPTY
        )
    }

    private fun getFiltersAsList(filterByGenres: String): List<Int> {
        val filtersList = arrayListOf<Int>()
        filterByGenres.split(",").forEach { genreString ->
            filtersList.add(genreString.toInt())
        }
        return filtersList
    }

    private fun getFiltersAsString(filterByGenres: List<Int>): String {
        var filtersString = ""
        filterByGenres.forEachIndexed { index, genre ->
            if (index == 0) {
                filtersString += genre
            } else {
                filtersString += ",$genre"
            }
        }
        return filtersString
    }

    private fun getYearFromYearString(filterByYear: String?): Int? {
        return try {
            filterByYear?.toInt()
        } catch (exception: NumberFormatException) {
            null
        }
    }
}