package com.frakton.moviesapp.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.frakton.moviesapp.models.Movie
import com.frakton.moviesapp.retrofit.MoviesApiServiceImpl

class MoviesRepository {
    private val TAG = "MoviesRepository"

    suspend fun getMoviesFromApi(): MutableLiveData<List<Movie>>? {
        var movies: MutableLiveData<List<Movie>>? = null
        MoviesApiServiceImpl.getMoviesFromApi().collect {
            Log.d(TAG, "got movies from api: ${it?.totalResults}")
            movies = MutableLiveData(it?.movies)
        }
        return movies
    }

}