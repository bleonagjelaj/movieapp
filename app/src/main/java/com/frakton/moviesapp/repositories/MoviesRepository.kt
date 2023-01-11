package com.frakton.moviesapp.repositories

import androidx.lifecycle.MutableLiveData
import com.frakton.moviesapp.models.MoviesResponse
import com.frakton.moviesapp.retrofit.RetrofitHelper


class MoviesRepository {
    private var moviesLiveData: MutableLiveData<ArrayList<MoviesResponse>> = MutableLiveData()
    private var moviesList: ArrayList<MoviesResponse> = ArrayList()

    suspend fun getMoviesFromApi(): MoviesResponse? {
        return RetrofitHelper.getMoviesApiService()?.getMovies()
    }
}