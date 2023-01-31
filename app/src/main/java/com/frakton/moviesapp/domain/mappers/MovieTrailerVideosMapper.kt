package com.frakton.moviesapp.domain.mappers

import com.frakton.moviesapp.data.retrofit.models.response.MovieTrailerVideosResponse
import com.frakton.moviesapp.data.retrofit.models.response.MovieVideoResults
import com.frakton.moviesapp.domain.models.TrailerDetails
import com.frakton.moviesapp.domain.models.TrailersVideosModel
import com.frakton.moviesapp.util.Constants.DEFAULT_THUMBNAIL_POSTFIX
import com.frakton.moviesapp.util.Constants.YOUTUBE_THUMBNAILS_URL

class MovieTrailerVideosMapper {

    fun map(movieTrailerVideosResponse: MovieTrailerVideosResponse): TrailersVideosModel {
        return TrailersVideosModel(
            movieId = movieTrailerVideosResponse.id,
            trailerVideos = getMovieTrailerVideosKeys(movieTrailerVideosResponse.results)
        )
    }

    private fun getMovieTrailerVideosKeys(movieVideoResults: List<MovieVideoResults>): List<TrailerDetails> {
        val movieTrailerVideosIds = ArrayList<TrailerDetails>()
        movieVideoResults.forEach {
            movieTrailerVideosIds.add(TrailerDetails(it.key, getMoviePosterPath(it.key), it.site))
        }
        return movieTrailerVideosIds
    }

    private fun getMoviePosterPath(posterPath: String?) =
        "$YOUTUBE_THUMBNAILS_URL$posterPath${DEFAULT_THUMBNAIL_POSTFIX}"
}