package com.frakton.moviesapp.domain

import androidx.paging.PagingData
import com.frakton.moviesapp.domain.base.BaseUseCase
import com.frakton.moviesapp.domain.models.MovieModel
import com.frakton.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface UseCases {
    interface GetMovies {
        suspend fun invoke(): Flow<Resource<PagingData<MovieModel>>>
    }

    interface SearchMovie :
        BaseUseCase<MovieParams.SearchMovieParams, Flow<Resource<PagingData<MovieModel>>>>
}