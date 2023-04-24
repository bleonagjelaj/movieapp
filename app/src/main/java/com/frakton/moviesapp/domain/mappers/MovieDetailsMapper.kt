package com.frakton.moviesapp.domain.mappers

import com.frakton.moviesapp.data.retrofit.models.response.Genre
import com.frakton.moviesapp.data.retrofit.models.response.MovieDetailsResponse
import com.frakton.moviesapp.data.retrofit.models.response.ProductionCompany
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import com.frakton.moviesapp.util.Constants
import com.frakton.moviesapp.util.EMPTY
import com.frakton.moviesapp.util.getYearFromDate
import com.frakton.moviesapp.util.roundToOneDecimalPlace

class MovieDetailsMapper {
    fun map(movieDetailsDataModel: MovieDetailsResponse): MovieDetailsModel {
        return MovieDetailsModel(
            id = movieDetailsDataModel.movieId,
            title = movieDetailsDataModel.title ?: String.EMPTY,
            releaseYear = getMovieReleaseYearFromDate(movieDetailsDataModel.releaseDate),
            posterPath = getMoviePosterPath(
                movieDetailsDataModel.posterPath ?: movieDetailsDataModel.backdropPath
            ),
            rating = getRatingFormatted(movieDetailsDataModel),
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

    private fun getRatingFormatted(movieDetailsDataModel: MovieDetailsResponse) =
        movieDetailsDataModel.voteAverage?.toFloat()?.roundToOneDecimalPlace() ?: 0F

    private fun getBudgetString(budget: Long?) =
        "$$budget"

    private fun getProductionCompanyString(productionCompanies: List<ProductionCompany>?): String {
        var productionCompanyString = ""
        productionCompanies?.forEachIndexed { index, productionCompany ->
            if(index == 0) {
                productionCompanyString += "${productionCompany.name}"
            } else {
                productionCompanyString += ", ${productionCompany.name}"
            }
        }
        return productionCompanyString
    }

    private fun getMovieGenresString(genres: List<Genre>?): List<String> {
        val movieGenresString = ArrayList<String>()
        genres?.forEach { genre ->
            genre.name?.let { genreName -> movieGenresString.add(genreName) }
        }
        return movieGenresString
    }

    private fun getMoviePosterPath(posterPath: String?) = "${Constants.MOVIES_IMAGE_URL}$posterPath"

    private fun getMovieReleaseYearFromDate(releaseDate: String?) =
        if (!releaseDate.isNullOrBlank()) "(${releaseDate.getYearFromDate()})" else String.EMPTY
}