package com.frakton.moviesapp.domain

import androidx.paging.PagingData
import com.frakton.moviesapp.data.retrofit.models.response.MovieDataModel
import com.frakton.moviesapp.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

interface UseCases {
    interface GetMovies {
        suspend fun invoke(): Flow<PagingData<MovieDataModel>>
    }

    interface SearchMovie :
        BaseUseCase<MovieParams.SearchMovieParams, Flow<PagingData<MovieDataModel>>>
}