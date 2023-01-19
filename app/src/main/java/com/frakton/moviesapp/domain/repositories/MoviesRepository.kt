package com.frakton.moviesapp.domain.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.frakton.moviesapp.data.retrofit.models.response.MovieDataModel
import com.frakton.moviesapp.domain.interactors.GetMoviesInteractor
import com.frakton.moviesapp.domain.interactors.SearchMovieInteractor
import com.frakton.moviesapp.domain.pagingsources.MoviePagingSource
import com.frakton.moviesapp.domain.pagingsources.SearchMoviePagingSource
import com.frakton.moviesapp.util.Constants
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val getMoviesInteractor: GetMoviesInteractor,
    private val searchMovieInteractor: SearchMovieInteractor
) {
    private val TAG = "MoviesRepository"

    fun getMoviesFromApi(): LiveData<PagingData<MovieDataModel>> {
        return Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                MoviePagingSource(getMoviesInteractor)
            }, initialKey = Constants.MOVIE_PAGER_INITIAL_KEY
        ).liveData
    }

    fun searchMovies(movieTitle: String): LiveData<PagingData<MovieDataModel>> {
        return Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                SearchMoviePagingSource(searchMovieInteractor, movieTitle)
            }, initialKey = Constants.MOVIE_PAGER_INITIAL_KEY
        ).liveData
    }

    private fun getPagingConfig() = PagingConfig(
        pageSize = Constants.PAGE_SIZE,
        enablePlaceholders = false,
        initialLoadSize = Constants.MOVIE_PAGER_INITIAL_LOAD_SIZE
    )
}