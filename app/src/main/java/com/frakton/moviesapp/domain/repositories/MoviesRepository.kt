package com.frakton.moviesapp.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.frakton.moviesapp.dependencyinjection.SearchMoviePagingSourceFactory
import com.frakton.moviesapp.domain.models.MovieModel
import com.frakton.moviesapp.domain.pagingsources.MoviePagingSource
import com.frakton.moviesapp.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesPagingSource: MoviePagingSource
) {
    private val TAG = "MoviesRepository"

    @Inject
    lateinit var searchMoviePagingSourceFactory: SearchMoviePagingSourceFactory

    fun getMoviesFromApi(): Flow<PagingData<MovieModel>> {
        return Pager(
            config = getPagingConfig(), pagingSourceFactory = {
                moviesPagingSource
            }, initialKey = Constants.MOVIE_PAGER_INITIAL_KEY
        ).flow
    }

    fun searchMovies(movieTitle: String): Flow<PagingData<MovieModel>> {
        return Pager(
            config = getPagingConfig(), pagingSourceFactory = {
                getSearchMoviePagingSource(movieTitle)
            }, initialKey = Constants.MOVIE_PAGER_INITIAL_KEY
        ).flow
    }

    private fun getPagingConfig() = PagingConfig(
        pageSize = Constants.PAGE_SIZE,
        enablePlaceholders = false,
        initialLoadSize = Constants.MOVIE_PAGER_INITIAL_LOAD_SIZE
    )

    private fun getSearchMoviePagingSource(movieTitle: String) =
        searchMoviePagingSourceFactory.create(movieTitle)
}