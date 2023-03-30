package com.frakton.moviesapp.domain

import androidx.paging.PagingData
import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.domain.base.BaseUseCase
import com.frakton.moviesapp.domain.models.*
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

    interface GetGenres {
        suspend fun invoke(): Flow<List<GenresModel>>
    }

    interface UpdateGenres {
        suspend fun invoke(genresList: List<GenresModel>)
    }

    interface GetGenresFromDB {
        suspend fun invoke(): Flow<List<GenreFilterModel>>
    }
}