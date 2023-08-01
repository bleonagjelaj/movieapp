package com.bleonahasanaj.moviesapp.testcases

import com.bleonahasanaj.moviesapp.data.superMarioMovie
import com.bleonahasanaj.moviesapp.data.retrofit.MoviesApiService
import com.bleonahasanaj.moviesapp.data.retrofit.RetrofitHelper
import com.bleonahasanaj.moviesapp.util.Constants.MOVIES_API_BASE_URL
import com.bleonahasanaj.moviesapp.util.Constants.PAGE_SIZE
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class RetrofitTest {
    private lateinit var moviesApiService: MoviesApiService

    @Before
    fun `init Movies Api Service instance`() {
        moviesApiService = RetrofitHelper.getMoviesApiService()
    }

    @Test
    fun `test Retrofit instance base URL`() {
        val retrofitInstance: Retrofit = RetrofitHelper.getInstance()
        assert(retrofitInstance.baseUrl().toString() == MOVIES_API_BASE_URL)
    }

    @Test
    fun `test Get Movies API endpoint`() {
        val pageIndex = 1
        val getMoviesResponse = runBlocking {
            moviesApiService.getMovies(pageIndex)
        }
        assert(getMoviesResponse.results.isNotEmpty())
        assert(getMoviesResponse.results.count() == PAGE_SIZE)
    }

    @Test
    fun `test Search Movies API endpoint`() {
        val pageIndex = 1
        val searchQuery = "Avatar"
        val searchMoviesResponse = runBlocking {
            moviesApiService.searchMovies(pageIndex, searchQuery)
        }
        assert(searchMoviesResponse.results.isNotEmpty())
    }

    @Test
    fun `test Get Movie By Id API endpoint`() {
        val getMovieWithIdResponse = runBlocking {
            moviesApiService.getMovieWithId(superMarioMovie.value.id)
        }
        assert(getMovieWithIdResponse.movieId == superMarioMovie.value.id)
    }

    @Test
    fun `test Get Movie Genres endpoint`() {
        val getGenresResponse = runBlocking {
            moviesApiService.getGenresList()
        }
        assert(getGenresResponse.genres.isNotEmpty())
    }

    @Test
    fun `test`() {
        val getMovieTrailerVideosResponse = runBlocking {
            moviesApiService.getMovieTrailerVideos(superMarioMovie.value.id)
        }
        assert(getMovieTrailerVideosResponse.results.isNotEmpty())
    }
}