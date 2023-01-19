package com.frakton.moviesapp.domain.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.frakton.moviesapp.data.retrofit.models.request.SearchMovieRequest
import com.frakton.moviesapp.data.retrofit.models.response.MovieDataModel
import com.frakton.moviesapp.domain.interactors.SearchMovieInteractor

class SearchMoviePagingSource(
    private val searchMovieInteractor: SearchMovieInteractor,
    private val movieTitle: String
) : PagingSource<Int, MovieDataModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDataModel> {
        return try {
            val position = params.key ?: 1
            val response = searchMovieInteractor.invoke(SearchMovieRequest(position, movieTitle))
            LoadResult.Page(
                data = response?.results!!,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDataModel>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}