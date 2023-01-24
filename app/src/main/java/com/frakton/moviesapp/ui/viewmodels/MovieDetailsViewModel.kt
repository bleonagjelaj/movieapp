package com.frakton.moviesapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frakton.moviesapp.domain.models.MovieDetailsModel

class MovieDetailsViewModel: ViewModel() {
    private val _movieDetails = MutableLiveData<MovieDetailsModel>()
    val movieDetails: MutableLiveData<MovieDetailsModel> = _movieDetails

    fun getMovieDetails(longExtra: Long) {
        //TODO: get movie details model from repository
    }
}