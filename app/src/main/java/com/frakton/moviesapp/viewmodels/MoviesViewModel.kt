package com.frakton.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.frakton.moviesapp.models.Movie
import com.frakton.moviesapp.repositories.MoviesRepository


class MoviesViewModel : ViewModel() {
    private var repository: MoviesRepository? = null

    init {
        repository = MoviesRepository()
    }

    fun loadMovies(): LiveData<PagingData<Movie>>? =
        repository?.getMoviesFromApi()?.cachedIn(viewModelScope)
}