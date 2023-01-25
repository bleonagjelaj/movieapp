package com.frakton.moviesapp.domain.repositories

import com.frakton.moviesapp.data.retrofit.models.request.GetMovieDetailsRequest
import com.frakton.moviesapp.domain.MovieParams
import com.frakton.moviesapp.domain.interactors.GetMovieDetailsInteractor
import com.frakton.moviesapp.domain.interactors.GetMovieTrailerVideosInteractor
import com.frakton.moviesapp.domain.mappers.MovieDetailsMapper
import com.frakton.moviesapp.domain.mappers.MovieTrailerVideosMapper
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import com.frakton.moviesapp.domain.models.MovieTrailerVideosModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(
    private val getMovieDetailsInteractor: GetMovieDetailsInteractor,
    private val getMovieTrailerVideosInteractor: GetMovieTrailerVideosInteractor,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val movieTrailerVideosMapper: MovieTrailerVideosMapper
) {
    fun getMovieDetails(input: MovieParams.GetMovieDetailsParams): Flow<MovieDetailsModel> = flow {
        emit(
            movieDetailsMapper.map(
                getMovieDetailsInteractor.invoke(
                    GetMovieDetailsRequest(
                        input.movieId
                    )
                )
            )
        )
    }

    fun getMovieTrailerVideos(input: MovieParams.GetMovieDetailsParams): Flow<MovieTrailerVideosModel> =
        flow {
            emit(
                movieTrailerVideosMapper.map(
                    getMovieTrailerVideosInteractor.invoke(
                        GetMovieDetailsRequest(
                            input.movieId
                        )
                    )
                )
            )
        }
}