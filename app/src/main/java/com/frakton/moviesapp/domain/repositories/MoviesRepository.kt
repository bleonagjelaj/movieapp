package com.frakton.moviesapp.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.domain.models.MovieModel
import com.frakton.moviesapp.domain.pagingsources.MoviePagingSource
import com.frakton.moviesapp.domain.pagingsources.SearchMoviePagingSource
import com.frakton.moviesapp.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesPagingSource: MoviePagingSource,
    private val searchMoviePagingSourceFactory: SearchMoviePagingSource.Factory
) {
    private val TAG = "MoviesRepository"

    fun getMoviesFromApi(filterRequest: Filters? = null): Flow<PagingData<MovieModel>> {
        filterRequest?.let { moviesPagingSource.setMoviesRequest(it) }
        return Pager(
            config = getPagingConfig(),
            pagingSourceFactory = { moviesPagingSource },
            initialKey = Constants.MOVIE_PAGER_INITIAL_KEY
        ).flow
    }

    fun searchMovies(movieTitle: String): Flow<PagingData<MovieModel>> {
        return Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                searchMoviePagingSourceFactory.createSearchMoviePagingSource(movieTitle)
            }, initialKey = Constants.MOVIE_PAGER_INITIAL_KEY
        ).flow
    }

    private fun getPagingConfig() = PagingConfig(
        pageSize = Constants.PAGE_SIZE,
        enablePlaceholders = false,
        initialLoadSize = Constants.MOVIE_PAGER_INITIAL_LOAD_SIZE
    )
}