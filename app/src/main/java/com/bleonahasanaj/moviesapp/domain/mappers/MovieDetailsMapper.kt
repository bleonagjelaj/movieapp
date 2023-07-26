package com.bleonahasanaj.moviesapp.domain.mappers

import com.bleonahasanaj.moviesapp.data.retrofit.models.response.Genre
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MovieDetailsResponse
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.ProductionCompany
import com.bleonahasanaj.moviesapp.domain.models.MovieDetailsModel
import com.bleonahasanaj.moviesapp.util.Constants
import com.bleonahasanaj.moviesapp.util.EMPTY
import com.bleonahasanaj.moviesapp.util.getYearFromDate

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
            genre.name?.let { genreName -> movieGenresString.add(genreName) }
        }
        return movieGenresString
    }

    private fun getMoviePosterPath(posterPath: String?) = "${Constants.MOVIES_IMAGE_URL}$posterPath"

    private fun getMovieReleaseYearFromDate(releaseDate: String?) =
        if (!releaseDate.isNullOrBlank()) "(${releaseDate.getYearFromDate()})" else String.EMPTY
}