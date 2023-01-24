package com.frakton.moviesapp.domain

import com.frakton.moviesapp.data.retrofit.models.request.GetMovieDetailsRequest
import com.frakton.moviesapp.data.retrofit.models.request.GetMoviesRequest
import com.frakton.moviesapp.data.retrofit.models.request.SearchMovieRequest
import com.frakton.moviesapp.data.retrofit.models.response.MovieDetailsResponse
import com.frakton.moviesapp.data.retrofit.models.response.MoviesResponse
import com.frakton.moviesapp.util.BaseInputOutputInteractor

interface MovieInteractors {
    interface GetMovies : BaseInputOutputInteractor<GetMoviesRequest, MoviesResponse>
    interface SearchMovie : BaseInputOutputInteractor<SearchMovieRequest, MoviesResponse>
    interface GetMovieDetails :
        BaseInputOutputInteractor<GetMovieDetailsRequest, MovieDetailsResponse>
}