package com.frakton.moviesapp.domain

import com.frakton.moviesapp.domain.base.BaseParameters

sealed interface MovieParams : BaseParameters {
    data class SearchMovieParams(
        val movieTitle: String
    ) : MovieParams

    data class GetMovieDetailsParams(
        val movieId: Long
    ) : MovieParams
}