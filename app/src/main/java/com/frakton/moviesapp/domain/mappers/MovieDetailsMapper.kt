package com.frakton.moviesapp.domain.mappers

import com.frakton.moviesapp.data.retrofit.models.response.Genre
import com.frakton.moviesapp.data.retrofit.models.response.MovieDetailsResponse
import com.frakton.moviesapp.data.retrofit.models.response.ProductionCompany
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import com.frakton.moviesapp.util.Constants
import com.frakton.moviesapp.util.EMPTY
import com.frakton.moviesapp.util.getYearFromDate

class MovieDetailsMapper {
    fun map(movieDetailsDataModel: MovieDetailsResponse): MovieDetailsModel {
        return MovieDetailsModel(
            id = movieDetailsDataModel.movieId,
            title = movieDetailsDataModel.title ?: String.EMPTY,
            releaseYear = getMovieReleaseYearFromDate(movieDetailsDataModel.releaseDate),
            posterPath = getMoviePosterPath(
                movieDetailsDataModel.posterPath ?: movieDetailsDataModel.backdropPath
            ),
            rating = movieDetailsDataModel.voteAverage?.toFloat() ?: 0F,
            description = movieDetailsDataModel.overview ?: String.EMPTY,
            genres = getMovieGenresString(movieDetailsDataModel.genres),
            productionCompany = getProductionCompanyString(
                movieDetailsDataModel.productionCompanies
            ),
            budget = getBudgetString(movieDetailsDataModel.budget),
            revenue = movieDetailsDataModel.revenue?.toString() ?: String.EMPTY,
            releaseDate = movieDetailsDataModel.releaseDate ?: String.EMPTY
        )
    }

    private fun getBudgetString(budget: Long?) =
        "$$budget"

    private fun getProductionCompanyString(productionCompanies: List<ProductionCompany>?): String {
        var productionCompanyString = ""
        productionCompanies?.forEach { productionCompany ->
            productionCompanyString += " ${productionCompany.name}"
        }
        return productionCompanyString
    }

    private fun getMovieGenresString(genres: List<Genre>?): List<String> {
        val movieGenresString = ArrayList<String>()
        genres?.forEach { genre ->
            movieGenresString.add(genre.name)
        }
        return movieGenresString
    }

    private fun getMoviePosterPath(posterPath: String?) = "${Constants.MOVIES_IMAGE_URL}$posterPath"

    private fun getMovieReleaseYearFromDate(releaseDate: String?) =
        releaseDate?.getYearFromDate() ?: String.EMPTY
}