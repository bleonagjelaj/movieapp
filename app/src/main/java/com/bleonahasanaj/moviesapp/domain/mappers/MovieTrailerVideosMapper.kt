package com.bleonahasanaj.moviesapp.domain.mappers

import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MovieTrailerVideosResponse
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MovieVideoResults
import com.bleonahasanaj.moviesapp.domain.models.TrailerDetails
import com.bleonahasanaj.moviesapp.domain.models.TrailersVideosModel
import com.bleonahasanaj.moviesapp.util.Constants.DEFAULT_VIDEO_THUMBNAIL_POSTFIX
import com.bleonahasanaj.moviesapp.util.Constants.YOUTUBE_THUMBNAILS_URL

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
        "$YOUTUBE_THUMBNAILS_URL$posterPath${DEFAULT_VIDEO_THUMBNAIL_POSTFIX}"
}