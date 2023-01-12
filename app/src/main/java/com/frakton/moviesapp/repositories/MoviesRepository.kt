package com.frakton.moviesapp.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.frakton.moviesapp.models.Movies
import com.frakton.moviesapp.retrofit.MoviesApiServiceImpl

class MoviesRepository {
    private val TAG = "MoviesRepository"

    suspend fun getMoviesFromApi(): MutableLiveData<List<Movies>>? {
        var movies: MutableLiveData<List<Movies>>? = null
        MoviesApiServiceImpl.getMoviesFromApi().collect {
            Log.d(TAG, "got movies from api: ${it?.totalResults}")
            movies = MutableLiveData(it?.movies)
        }
        return movies
    }

}