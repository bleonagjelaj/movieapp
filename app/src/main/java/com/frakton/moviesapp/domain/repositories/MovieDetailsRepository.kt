package com.frakton.moviesapp.domain.repositories

import com.frakton.moviesapp.data.retrofit.models.request.GetMovieDetailsRequest
import com.frakton.moviesapp.domain.MovieParams
import com.frakton.moviesapp.domain.interactors.GetMovieDetailsInteractor
import com.frakton.moviesapp.domain.mappers.MovieDetailsMapper
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(
    private val getMovieDetailsInteractor: GetMovieDetailsInteractor,
    private val movieDetailsMapper: MovieDetailsMapper
) {
    fun getMovieDetails(input: MovieParams.GetMovieDetailsParams): Flow<MovieDetailsModel> {
        return flow {
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
    }
}