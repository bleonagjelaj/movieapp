package com.bleonahasanaj.moviesapp.domain.repositories

import com.bleonahasanaj.moviesapp.data.retrofit.models.request.GetMovieDetailsRequest
import com.bleonahasanaj.moviesapp.domain.MovieParams
import com.bleonahasanaj.moviesapp.domain.interactors.GetMovieDetailsInteractor
import com.bleonahasanaj.moviesapp.domain.interactors.GetMovieTrailerVideosInteractor
import com.bleonahasanaj.moviesapp.domain.mappers.MovieDetailsMapper
import com.bleonahasanaj.moviesapp.domain.mappers.MovieTrailerVideosMapper
import com.bleonahasanaj.moviesapp.domain.models.MovieDetailsModel
import com.bleonahasanaj.moviesapp.domain.models.TrailersVideosModel
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
                movieDetailsDataModel = getMovieDetailsInteractor(
                    GetMovieDetailsRequest(
                        movieId = input.movieId
                    )
                )
            )
        )
    }

    fun getMovieTrailerVideos(input: MovieParams.GetMovieDetailsParams): Flow<TrailersVideosModel> =
        flow {
            emit(
                movieTrailerVideosMapper.map(
                    movieTrailerVideosResponse = getMovieTrailerVideosInteractor(
                        GetMovieDetailsRequest(
                            movieId = input.movieId
                        )
                    )
                )
            )
        }
}