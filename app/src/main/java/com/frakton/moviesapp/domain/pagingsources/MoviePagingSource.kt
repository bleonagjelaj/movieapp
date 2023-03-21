package com.frakton.moviesapp.domain.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.frakton.moviesapp.data.retrofit.models.request.GetMoviesRequest
import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.domain.interactors.GetMoviesInteractor
import com.frakton.moviesapp.domain.mappers.MoviesMapper
import com.frakton.moviesapp.domain.models.MovieModel
import com.frakton.moviesapp.util.Constants
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val getMoviesInteractor: GetMoviesInteractor,
    private val moviesMapper: MoviesMapper
) : PagingSource<Int, MovieModel>() {
    private var movieFilters: Filters? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val position = params.key ?: Constants.MOVIE_PAGER_INITIAL_KEY
        return try {
            val response = getMoviesInteractor.invoke(
                GetMoviesRequest(
                    page = position,
                    movieFilters = movieFilters
                )
            ).results.map(moviesMapper::map)
            LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    fun setMoviesRequest(filters: Filters) {
        this.movieFilters = filters
    }
}