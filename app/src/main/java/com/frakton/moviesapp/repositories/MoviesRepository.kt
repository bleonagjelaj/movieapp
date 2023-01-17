package com.frakton.moviesapp.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.frakton.moviesapp.models.Movie
import com.frakton.moviesapp.pagingsources.MoviePagingSource
import com.frakton.moviesapp.pagingsources.SearchMoviePagingSource
import com.frakton.moviesapp.utils.Constants

class MoviesRepository {
    private val TAG = "MoviesRepository"

    fun getMoviesFromApi(): LiveData<PagingData<Movie>> {
        return Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                MoviePagingSource()
            }, initialKey = Constants.MOVIE_PAGER_INITIAL_KEY
        ).liveData
    }

    fun searchMovies(movieTitle: String): LiveData<PagingData<Movie>> {
        return Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                SearchMoviePagingSource(movieTitle)
            }, initialKey = Constants.MOVIE_PAGER_INITIAL_KEY
        ).liveData
    }

    private fun getPagingConfig() = PagingConfig(
        pageSize = Constants.PAGE_SIZE,
        enablePlaceholders = false,
        initialLoadSize = Constants.MOVIE_PAGER_INITIAL_LOAD_SIZE
    )
}