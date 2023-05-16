package com.frakton.moviesapp.testcases

import com.frakton.moviesapp.data.superMarioMovie
import com.frakton.moviesapp.data.retrofit.RetrofitHelper
import com.frakton.moviesapp.domain.mappers.MovieDetailsMapper
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetMovieByIdTest {
    @Test
    fun `test getting and mapping movie details by movie ID`() {
        val movie = runBlocking {
            MovieDetailsMapper().map(
                RetrofitHelper.getMoviesApiService().getMovieWithId(superMarioMovie.value.id)
            )
        }
        with(superMarioMovie.value) {
            assert(id == movie.id)
            assert(title == movie.title)
            assert(rating == movie.rating)
            assert(posterPath == movie.posterPath)
            assert(description == movie.description)
            assert(releaseDate == movie.releaseDate)
            assert(revenue == movie.revenue)
            assert(budget == movie.budget)
            assert(productionCompany == movie.productionCompany)
            assert(genres == movie.genres)
            assert(releaseYear == movie.releaseYear)
        }
    }
}