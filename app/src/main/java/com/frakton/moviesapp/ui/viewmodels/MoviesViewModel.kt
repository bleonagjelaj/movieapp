package com.frakton.moviesapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.domain.MovieParams
import com.frakton.moviesapp.domain.models.MovieFiltersModel
import com.frakton.moviesapp.domain.models.MovieModel
import com.frakton.moviesapp.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val getFiltersUseCase: GetFiltersUseCase,
    private val getFiltersInitialStateUseCase: GetFiltersInitialStateUseCase,
    private val updateFiltersUseCase: UpdateFiltersUseCase
) : ViewModel() {
    private val _movieData = MutableLiveData<PagingData<MovieModel>>()
    val movieData: MutableLiveData<PagingData<MovieModel>> = _movieData

    private val _filtersData: MutableLiveData<MovieFiltersModel> = MutableLiveData()
    val filtersData = _filtersData

    fun loadMovies() =
        viewModelScope.launch {
            var movieFilters: Filters? = null

            getFiltersUseCase().collect { movieFiltersResponse ->
                movieFilters = movieFiltersResponse
            }

            getMoviesUseCase(movieFilters)
                .cachedIn(this)
                .collect { moviesPagingData ->
                    _movieData.value = moviesPagingData
                }
        }

    fun searchMovies(movieTitle: String) =
        viewModelScope.launch {
            searchMovieUseCase(MovieParams.SearchMovieParams(movieTitle))
                .cachedIn(this)
                .collect { searchMoviePagingData ->
                    _movieData.value = searchMoviePagingData
                }
        }

    fun getFilters() {
        viewModelScope.launch {
            getFiltersInitialStateUseCase().collect { movieFilters ->
                _filtersData.value = movieFilters
            }
        }
    }

    fun updateFilters(filters: MovieFiltersModel) {
        viewModelScope.launch {
            val filtersGotUpdated = updateFiltersUseCase(filters)
            if (filtersGotUpdated) {
                loadMovies()
            }
        }
    }
}