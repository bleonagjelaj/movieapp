package com.frakton.moviesapp.domain.enums

import android.content.Context
import com.frakton.moviesapp.R

enum class SortFiltersEnum(val filterStringRes: Int, val filterId: String) {
    POPULARITY_ASC(R.string.popularity, "popularity"),
    RELEASE_DATE_ASC(R.string.release_date, "release_date"),
    REVENUE_ASC(R.string.revenue, "revenue"),
    VOTE_AVERAGE_ASC(R.string.vote_average, "vote_average"),
    MOVIE_TITLE_ASC(R.string.movie_title, "movie_title");

    companion object {
        fun getSortFilters(context: Context): ArrayList<String> {
            val sortFilterStringResources = arrayListOf<String>()
            values().forEach {
                sortFilterStringResources.add(context.getString(it.filterStringRes))
            }
            return sortFilterStringResources
        }
    }
}