package com.frakton.moviesapp.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.frakton.moviesapp.data.retrofit.models.response.MovieDataModel
import com.frakton.moviesapp.domain.base.BaseUseCase
import com.frakton.moviesapp.domain.models.MoviesModel

interface UseCases {
    interface GetMovies: BaseUseCase<MovieParams.GetMoviesParams, LiveData<PagingData<MovieDataModel>>>
    interface SearchMovie: BaseUseCase<MovieParams.SearchMovieParams, LiveData<PagingData<MovieDataModel>>>
}