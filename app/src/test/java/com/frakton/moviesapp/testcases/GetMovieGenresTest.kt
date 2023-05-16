package com.frakton.moviesapp.testcases

import com.frakton.moviesapp.data.movieGenres
import com.frakton.moviesapp.data.retrofit.RetrofitHelper
import com.frakton.moviesapp.domain.mappers.GenresMapper
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetMovieGenresTest {
    @Test
    fun `test getting and mapping movie genres`() {
        val genres = runBlocking {
            GenresMapper().map(
                RetrofitHelper.getMoviesApiService().getGenresList()
            )
        }
        movieGenres.value.genreList.forEach { genresModel ->
            assert(genresModel in genres)
            val genreToCompare = genres.firstOrNull { it.id == genresModel.id }
            assert(genreToCompare?.name == genresModel.name)
        }
    }
}