package com.frakton.moviesapp.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.frakton.moviesapp.models.Movie
import com.frakton.moviesapp.retrofit.RetrofitHelper

class SearchMoviePagingSource(private val movieTitle: String) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val position = params.key ?: 1
            val response = RetrofitHelper.getMoviesApiService()?.searchMovies(position, movieTitle)
            LoadResult.Page(
                data = response?.body()?.movies!!,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}