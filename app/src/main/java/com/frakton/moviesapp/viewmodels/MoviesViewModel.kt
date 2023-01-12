package com.frakton.moviesapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frakton.moviesapp.models.Movies
import com.frakton.moviesapp.repositories.MoviesRepository


class MoviesViewModel: ViewModel() {
    private var repository: MoviesRepository? = null
    init {
        repository = MoviesRepository()
    }

    suspend fun loadMovies(): MutableLiveData<List<Movies>>? {
        return repository?.getMoviesFromApi()
    }
}