package com.bleonahasanaj.moviesapp.testcases

import com.bleonahasanaj.moviesapp.data.retrofit.RetrofitHelper
import com.bleonahasanaj.moviesapp.data.ratatouilleMovieTrailers
import com.bleonahasanaj.moviesapp.domain.mappers.MovieTrailerVideosMapper
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetMovieTrailerVideosTest {
    @Test
    fun `test getting and mapping movie trailers by movie ID`() {
        val movieTrailersFromApi = runBlocking {
            MovieTrailerVideosMapper().map(
                RetrofitHelper.getMoviesApiService()
                    .getMovieTrailerVideos(ratatouilleMovieTrailers.value.movieId)
            )
        }
        ratatouilleMovieTrailers.value.trailerVideos.forEach { trailer ->
            val trailerFromApi =
                movieTrailersFromApi.trailerVideos.firstOrNull { it.key == trailer.key }
            assert(trailerFromApi?.thumbnailPath == trailer.thumbnailPath)
            assert(trailerFromApi?.site == trailer.site)
        }
    }
}