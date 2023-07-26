package com.bleonahasanaj.moviesapp.domain.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bleonahasanaj.moviesapp.data.retrofit.models.request.SearchMovieRequest
import com.bleonahasanaj.moviesapp.domain.interactors.SearchMovieInteractor
import com.bleonahasanaj.moviesapp.domain.mappers.MoviesMapper
import com.bleonahasanaj.moviesapp.domain.models.MovieModel
import javax.inject.Inject

class SearchMoviePagingSource(
    private val searchMovieInteractor: SearchMovieInteractor,
    private val moviesMapper: MoviesMapper,
    private val movieTitle: String
) : PagingSource<Int, MovieModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val position = params.key ?: 1
            val response = arrayListOf<MovieModel>()
            searchMovieInteractor.invoke(
                SearchMovieRequest(
                    page = position,
                    movieTitle = movieTitle
                )
            ).results.forEach {
                response.add(moviesMapper.map(it))
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    class Factory @Inject constructor(
        private val searchMovieInteractor: SearchMovieInteractor,
        private val moviesMapper: MoviesMapper
    ) {
        fun createSearchMoviePagingSource(movieTitle: String) =
            SearchMoviePagingSource(searchMovieInteractor, moviesMapper, movieTitle)
    }
}