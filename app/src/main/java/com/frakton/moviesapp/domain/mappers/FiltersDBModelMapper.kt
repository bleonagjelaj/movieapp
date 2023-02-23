package com.frakton.moviesapp.domain.mappers

import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.domain.models.MovieFiltersModel

class FiltersDBModelMapper {
    fun map(filters: Filters?): MovieFiltersModel {
        return MovieFiltersModel(
            sortBy = getSortByValue(filters?.sortBy),
            ordering = getOrderingValue(filters?.sortBy),
            filterByYear = filters?.filterByYear?.toString(),
            filterByGenres = getFiltersAsList(filters?.filterByGenres)
        )
    }

    private fun getOrderingValue(sortBy: String?): String? {
        return sortBy?.split(".")?.get(1)
    }

    private fun getSortByValue(sortBy: String?): String? {
        return sortBy?.split(".")?.get(0)
    }

    private fun getFiltersAsList(filterByGenres: String?): List<Int>? {
        val filtersList = arrayListOf<Int>()
        if(filterByGenres?.isNotBlank() == true) {
            filterByGenres.split(",").forEach { genreString ->
                filtersList.add(genreString.toInt())
            }
        }
        return filtersList.ifEmpty { null }
    }
}