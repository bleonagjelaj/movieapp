package com.frakton.moviesapp.domain.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.frakton.moviesapp.data.retrofit.models.request.SearchMovieRequest
import com.frakton.moviesapp.domain.interactors.SearchMovieInteractor
import com.frakton.moviesapp.domain.mappers.MoviesMapper
import com.frakton.moviesapp.domain.models.MovieModel
import javax.inject.Inject

class SearchMoviePagingSource(
    private val searchMovieInteractor: SearchMovieInteractor,
    private val moviesMapper: MoviesMapper,
    private val movieTitle: String
) : PagingSource<Int, MovieModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val position = params.key ?: 1
            val response = searchMovieInteractor.invoke(SearchMovieRequest(position, movieTitle))
            LoadResult.Page(
                data = response.results.map(moviesMapper::map),
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