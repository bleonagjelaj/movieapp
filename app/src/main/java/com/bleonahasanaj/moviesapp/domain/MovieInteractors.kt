package com.bleonahasanaj.moviesapp.domain

import com.bleonahasanaj.moviesapp.data.retrofit.models.request.GetMovieDetailsRequest
import com.bleonahasanaj.moviesapp.data.retrofit.models.request.GetMoviesRequest
import com.bleonahasanaj.moviesapp.data.retrofit.models.request.SearchMovieRequest
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.GenreResponse
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MovieDetailsResponse
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MovieTrailerVideosResponse
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MoviesResponse
import com.bleonahasanaj.moviesapp.util.BaseInputOutputInteractor

interface MovieInteractors {
    interface GetMovies : BaseInputOutputInteractor<GetMoviesRequest, MoviesResponse>
    interface SearchMovie : BaseInputOutputInteractor<SearchMovieRequest, MoviesResponse>
    interface GetMovieDetails :
        BaseInputOutputInteractor<GetMovieDetailsRequest, MovieDetailsResponse>

    interface GetMovieTrailerVideos :
        BaseInputOutputInteractor<GetMovieDetailsRequest, MovieTrailerVideosResponse>

    interface GetGenres : BaseInputOutputInteractor<Unit?, GenreResponse>
}