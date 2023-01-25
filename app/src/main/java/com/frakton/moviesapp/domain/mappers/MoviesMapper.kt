package com.frakton.moviesapp.domain.mappers

import com.frakton.moviesapp.data.retrofit.models.response.MovieDataModel
import com.frakton.moviesapp.domain.enums.MovieGenreEnum
import com.frakton.moviesapp.domain.models.MovieModel
import com.frakton.moviesapp.util.Constants
import com.frakton.moviesapp.util.EMPTY
import com.frakton.moviesapp.util.formatDateString

class MoviesMapper {
    fun map(movieDataModel: MovieDataModel): MovieModel {
        return MovieModel(
            movieId = movieDataModel.id,
            movieGenres = getGenres(movieDataModel.genreIds),
            moviePosterPath = getMoviePosterPath(
                movieDataModel.posterPath ?: movieDataModel.backdropPath ?: String.EMPTY
            ),
            movieReleaseDate = formatReleaseDate(movieDataModel.releaseDate),
            movieRating = divideMovieRateInHalf(movieDataModel.voteAverage)
        )
    }

    private fun divideMovieRateInHalf(voteAverage: Float?) = (voteAverage ?: 0F) / 2F

    private fun getMoviePosterPath(posterPath: String) = "${Constants.MOVIES_IMAGE_URL}$posterPath"

    private fun getGenres(genreIds: List<Int>?): String {
        var genresString = ""
        genreIds?.forEachIndexed { i: Int, genreId: Int ->
            genresString += MovieGenreEnum.getGenreById(genreId)
            if (genreIds.lastIndex != i) {
                genresString += " | "
            }
        }
        return genresString
    }

    private fun formatReleaseDate(releaseDate: String?): String {
        return if (releaseDate.isNullOrBlank()) "" else releaseDate.formatDateString()
    }
}