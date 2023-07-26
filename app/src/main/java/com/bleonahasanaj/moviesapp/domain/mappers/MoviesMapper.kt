package com.bleonahasanaj.moviesapp.domain.mappers

import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MovieDataModel
import com.bleonahasanaj.moviesapp.domain.models.MovieModel
import com.bleonahasanaj.moviesapp.domain.usecases.GetGenresFromDBUseCase
import com.bleonahasanaj.moviesapp.util.Constants
import com.bleonahasanaj.moviesapp.util.EMPTY
import com.bleonahasanaj.moviesapp.util.formatDateString
import javax.inject.Inject

class MoviesMapper @Inject constructor(
    private val getGenresFromDBUseCase: GetGenresFromDBUseCase
) {
    suspend fun map(movieDataModel: MovieDataModel): MovieModel {
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

    private suspend fun getGenres(genreIds: List<Int>?): String {
        var genresString = ""
        getGenresFromDBUseCase().collect { genresListFromDb ->
            genreIds?.forEachIndexed { i: Int, genreId: Int ->
                val genreName = genresListFromDb.firstOrNull { it.id == genreId }?.name
                if (!genreName.isNullOrEmpty()) {
                    genresString += genreName
                    if (genreIds.lastIndex != i) {
                        genresString += " / "
                    }
                }
            }
        }
        return genresString
    }

    private fun formatReleaseDate(releaseDate: String?): String {
        return if (releaseDate.isNullOrBlank()) "" else releaseDate.formatDateString()
    }
}