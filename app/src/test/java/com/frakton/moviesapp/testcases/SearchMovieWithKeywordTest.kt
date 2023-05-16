package com.frakton.moviesapp.testcases

import com.frakton.moviesapp.data.retrofit.RetrofitHelper
import com.frakton.moviesapp.data.searchForWolfOfWallStreet
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchMovieWithKeywordTest {
    private val searchKeyword = "the wolf of wall"
    private val pageIndex = 1

    @Test
    fun `test getting the movie list by searching with keyword`() {
        val searchResult = runBlocking {
            RetrofitHelper.getMoviesApiService()
                .searchMovies(pageIndex = pageIndex, movieTitle = searchKeyword)
        }
        with(searchForWolfOfWallStreet.value) {
            assert(page == searchResult.page)
            results.forEach { movieDataModel ->
                val movieToCompare = searchResult.results.firstOrNull { it.id == movieDataModel.id }
                with(movieDataModel) {
                    assert(posterPath == movieToCompare?.posterPath)
                    assert(adult == movieToCompare?.adult)
                    assert(video == movieToCompare?.video)
                    assert(overview == movieToCompare?.overview)
                    assert(originalLanguage == movieToCompare?.originalLanguage)
                    assert(originalTitle == movieToCompare?.originalTitle)
                    assert(releaseDate == movieToCompare?.releaseDate)
                    assert(title == movieToCompare?.title)
                    assert(backdropPath == movieToCompare?.backdropPath)
                    assert(popularity == movieToCompare?.popularity)
                    assert(voteAverage == movieToCompare?.voteAverage)
                    assert(voteCount == movieToCompare?.voteCount)
                    assert(movieToCompare?.genreIds?.isNotEmpty() == true)
                    assert(genreIds?.size == movieToCompare?.genreIds?.size)
                    if (!genreIds.isNullOrEmpty() && !movieToCompare?.genreIds.isNullOrEmpty()
                    ) {
                        movieToCompare?.genreIds?.forEach { genreIdToCompare ->
                                assert(genreIdToCompare in genreIds!!)
                            }
                        }
                    }
            }
            assert(totalPages == searchResult.totalPages)
            assert(totalResults == searchResult.totalResults)
        }
    }
}