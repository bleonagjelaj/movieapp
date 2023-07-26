package com.bleonahasanaj.moviesapp.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bleonahasanaj.moviesapp.db.tables.Filters
import com.bleonahasanaj.moviesapp.domain.models.MovieModel
import com.bleonahasanaj.moviesapp.domain.pagingsources.MoviePagingSource
import com.bleonahasanaj.moviesapp.domain.pagingsources.SearchMoviePagingSource
import com.bleonahasanaj.moviesapp.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesPagingSource: MoviePagingSource,
    private val searchMoviePagingSourceFactory: SearchMoviePagingSource.Factory
) {
    fun getMoviesFromApi(filtersRequest: Filters? = null): Flow<PagingData<MovieModel>> {
        filtersRequest?.let { moviesPagingSource.setMoviesRequest(it) }
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
            },
            initialKey = Constants.MOVIE_PAGER_INITIAL_KEY
        ).flow
    }

    private fun getPagingConfig() = PagingConfig(
        pageSize = Constants.PAGE_SIZE,
        enablePlaceholders = false,
        initialLoadSize = Constants.MOVIE_PAGER_INITIAL_LOAD_SIZE
    )
}