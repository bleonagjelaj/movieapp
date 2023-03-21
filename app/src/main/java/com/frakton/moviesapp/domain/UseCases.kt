package com.frakton.moviesapp.domain

import androidx.paging.PagingData
import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.domain.base.BaseUseCase
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import com.frakton.moviesapp.domain.models.MovieFiltersModel
import com.frakton.moviesapp.domain.models.MovieModel
import com.frakton.moviesapp.domain.models.TrailersVideosModel
import kotlinx.coroutines.flow.Flow

interface UseCases {
    interface GetMovies {
        suspend fun invoke(filtersRequest: Filters? = null): Flow<PagingData<MovieModel>>
    }

    interface SearchMovie : BaseUseCase<MovieParams.SearchMovieParams, Flow<PagingData<MovieModel>>>

    interface GetMovieDetails :
        BaseUseCase<MovieParams.GetMovieDetailsParams, Flow<MovieDetailsModel>>

    interface GetMovieVideoTrailers :
        BaseUseCase<MovieParams.GetMovieDetailsParams, Flow<TrailersVideosModel>>

    interface GetFilters {
        suspend fun invoke(): Flow<Filters?>
    }

    interface GetFiltersInitialState {
        suspend fun invoke(): Flow<MovieFiltersModel>
    }

    interface UpdateFilters {
        suspend fun invoke(movieFilters: MovieFiltersModel): Boolean
    }
}