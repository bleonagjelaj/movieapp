package com.frakton.moviesapp.domain

import com.frakton.moviesapp.domain.base.BaseParameters

sealed interface MovieParams: BaseParameters {
    data class GetMoviesParams(
        val page: Int
    ) : MovieParams

    data class SearchMovieParams(
        val page: Int,
        val movieTitle: String
    ) : MovieParams
}