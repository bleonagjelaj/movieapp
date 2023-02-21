package com.frakton.moviesapp.domain.enums

import android.content.Context
import com.frakton.moviesapp.R

enum class SortFiltersEnum(val filterStringRes: Int, val filterId: String) {
    POPULARITY_ASC(R.string.popularity, "popularity.asc"),
    POPULARITY_DESC(R.string.popularity, "popularity.desc"),
    RELEASE_DATE_ASC(R.string.release_date, "release_date.asc"),
    RELEASE_DATE_DESC(R.string.release_date, "release_date.asc"),
    REVENUE_ASC(R.string.revenue, "revenue.asc"),
    REVENUE_DESC(R.string.revenue, "revenue.asc"),
    VOTE_AVERAGE_ASC(R.string.vote_average, "vote_average.asc"),
    VOTE_AVERAGE_DESC(R.string.vote_average, "vote_average.asc"),
    MOVIE_TITLE_ASC(R.string.movie_title, "movie_title.asc"),
    MOVIE_TITLE_DESC(R.string.movie_title, "movie_title.asc");

    companion object {
        fun getDistinctFilterStringResources(context: Context): List<String> {
            val sortFilterStringResources = arrayListOf<String>()
            values().distinctBy { it.filterStringRes }.forEach {
                sortFilterStringResources.add(context.getString(it.filterStringRes))
            }
            return sortFilterStringResources
        }
    }
}