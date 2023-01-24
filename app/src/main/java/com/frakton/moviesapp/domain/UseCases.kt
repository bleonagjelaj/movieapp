package com.frakton.moviesapp.domain

import androidx.paging.PagingData
import com.frakton.moviesapp.domain.base.BaseUseCase
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import com.frakton.moviesapp.domain.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface UseCases {
    interface GetMovies {
        suspend fun invoke(): Flow<PagingData<MovieModel>>
    }

    interface SearchMovie : BaseUseCase<MovieParams.SearchMovieParams, Flow<PagingData<MovieModel>>>

    interface GetMovieDetails :
        BaseUseCase<MovieParams.GetMovieDetailsParams, Flow<MovieDetailsModel>>
}