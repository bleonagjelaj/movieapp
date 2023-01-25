package com.frakton.moviesapp.domain.mappers

import com.frakton.moviesapp.data.retrofit.models.response.MovieTrailerVideosResponse
import com.frakton.moviesapp.data.retrofit.models.response.MovieVideoResults
import com.frakton.moviesapp.domain.models.MovieTrailerVideosModel

class MovieTrailerVideosMapper {

    fun map(movieTrailerVideosResponse: MovieTrailerVideosResponse): MovieTrailerVideosModel {
        return MovieTrailerVideosModel(
            movieId = movieTrailerVideosResponse.id,
            trailerVideoIds = getMovieTrailerVideosIds(movieTrailerVideosResponse.results)
        )
    }

    private fun getMovieTrailerVideosIds(movieVideoResults: List<MovieVideoResults>): List<String> {
        val movieTrailerVideosIds = ArrayList<String>()
        movieVideoResults.forEach {
            movieTrailerVideosIds.add(it.id)
        }
        return movieTrailerVideosIds
    }
}