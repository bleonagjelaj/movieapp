package com.frakton.moviesapp.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.frakton.moviesapp.models.Movie

class MoviesRepository {
    private val TAG = "MoviesRepository"

    fun getMoviesFromApi(): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                MoviePagingSource()
            }, initialKey = 1
        ).liveData
    }

}