package com.bleonahasanaj.moviesapp.domain.enums

import android.content.Context
import com.bleonahasanaj.moviesapp.R

enum class SortFiltersEnum(val filterStringRes: Int, val filterId: String) {
    POPULARITY(R.string.popularity, "popularity"),
    RELEASE_DATE(R.string.release_date, "release_date"),
    RATING(R.string.rating, "vote_average"),
    MOVIE_TITLE(R.string.movie_title, "movie_title");

    companion object {
        fun getSortFilters(context: Context): ArrayList<String> {
            val sortFilterStringResources = arrayListOf<String>()
            values().forEach {
                sortFilterStringResources.add(context.getString(it.filterStringRes))
            }
            return sortFilterStringResources
        }

        fun getSortFilterIdByName(filterName: String, context: Context) =
            values().firstOrNull { context.getString(it.filterStringRes) == filterName }?.filterId

        fun getSortFilterNameFromId(filterId: String?, context: Context) =
            values().firstOrNull { it.filterId == filterId }?.filterStringRes?.let { filterStringRes ->
                context.getString(filterStringRes)
            }
    }
}