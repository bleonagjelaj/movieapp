package com.bleonahasanaj.moviesapp.domain

import com.bleonahasanaj.moviesapp.domain.base.BaseParameters

sealed interface MovieParams : BaseParameters {
    data class SearchMovieParams(
        val movieTitle: String
    ) : MovieParams

    data class GetMovieDetailsParams(
        val movieId: Long
    ) : MovieParams
}