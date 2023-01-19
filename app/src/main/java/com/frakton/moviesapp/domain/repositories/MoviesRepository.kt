package com.frakton.moviesapp.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.frakton.moviesapp.domain.interactors.GetMoviesInteractor
import com.frakton.moviesapp.domain.interactors.SearchMovieInteractor
import com.frakton.moviesapp.domain.mappers.MoviesMapper
import com.frakton.moviesapp.domain.models.MovieModel
import com.frakton.moviesapp.domain.pagingsources.MoviePagingSource
import com.frakton.moviesapp.domain.pagingsources.SearchMoviePagingSource
import com.frakton.moviesapp.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val getMoviesInteractor: GetMoviesInteractor,
    private val searchMovieInteractor: SearchMovieInteractor,
    private val moviesMapper: MoviesMapper
) {
    private val TAG = "MoviesRepository"

    fun getMoviesFromApi(): Flow<PagingData<MovieModel>> {
        return Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                MoviePagingSource(getMoviesInteractor, moviesMapper)
            }, initialKey = Constants.MOVIE_PAGER_INITIAL_KEY
        ).flow
    }

    fun searchMovies(movieTitle: String): Flow<PagingData<MovieModel>> {
        return Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                SearchMoviePagingSource(searchMovieInteractor, movieTitle, moviesMapper)
            }, initialKey = Constants.MOVIE_PAGER_INITIAL_KEY
        ).flow
    }

    private fun getPagingConfig() = PagingConfig(
        pageSize = Constants.PAGE_SIZE,
        enablePlaceholders = false,
        initialLoadSize = Constants.MOVIE_PAGER_INITIAL_LOAD_SIZE
    )
}