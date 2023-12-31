package com.bleonahasanaj.moviesapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bleonahasanaj.moviesapp.R
import com.bleonahasanaj.moviesapp.db.tables.Filters
import com.bleonahasanaj.moviesapp.domain.MovieParams
import com.bleonahasanaj.moviesapp.domain.enums.SortFiltersEnum
import com.bleonahasanaj.moviesapp.domain.models.GenreFilterModel
import com.bleonahasanaj.moviesapp.domain.models.MovieFiltersModel
import com.bleonahasanaj.moviesapp.domain.models.MovieModel
import com.bleonahasanaj.moviesapp.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val getFiltersUseCase: GetFiltersUseCase,
    private val getFiltersInitialStateUseCase: GetFiltersInitialStateUseCase,
    private val updateFiltersUseCase: UpdateFiltersUseCase,
    private val getGenresFromDBUseCase: GetGenresFromDBUseCase
) : ViewModel() {
    private var isFirstLoad = true
    private val yearsList = arrayListOf<String>()

    private val _movieData = MutableLiveData<PagingData<MovieModel>>()
    val movieData: MutableLiveData<PagingData<MovieModel>> = _movieData

    private val _sortByData: MutableLiveData<List<String>> = MutableLiveData()
    val sortByData = _sortByData

    private val _yearsData: MutableLiveData<List<String>> = MutableLiveData()
    val yearsData = _yearsData

    private val _genresData: MutableLiveData<List<Int>> = MutableLiveData()
    val genresData = _genresData

    private val _genreFiltersData: MutableLiveData<List<GenreFilterModel>> = MutableLiveData()
    val genreFiltersData = _genreFiltersData

    private val _sortBySpinnerSelection: MutableLiveData<Int> = MutableLiveData()
    val sortBySpinnerSelection = _sortBySpinnerSelection

    private val _filterByYearSpinnerSelection: MutableLiveData<Int> = MutableLiveData()
    val filterByYearSpinnerSelection = _filterByYearSpinnerSelection

    private val _shouldToggleOrdering: MutableLiveData<String> = MutableLiveData()
    val shouldToggleOrdering = _shouldToggleOrdering

    fun loadMovies(shouldReload: Boolean = false) =
        viewModelScope.launch {
            if (isFirstLoad || shouldReload) {
                var movieFilters: Filters? = null

                getFiltersUseCase().collect { movieFiltersResponse ->
                    movieFilters = movieFiltersResponse
                }

                getMoviesUseCase(movieFilters)
                    .cachedIn(this)
                    .collect { moviesPagingData ->
                        _movieData.value = moviesPagingData
                    }
                isFirstLoad = false
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

    fun getFilters(context: Context) {
        viewModelScope.launch {
            getFiltersInitialStateUseCase().collect { movieFilters ->
                setSortBySpinnerSelection(context, movieFilters.sortBy)
                setFilterByYearSpinnerSelection(movieFilters.filterByYear)
                setOrderingIconStatus(movieFilters.ordering)
                setGenresList(movieFilters.filterByGenres)
            }
        }
    }

    private fun setGenresList(genresValue: List<Int>?) {
        genresValue?.let { _genresData.value = it }
    }

    private fun setOrderingIconStatus(ordering: String?) {
        ordering?.let { _shouldToggleOrdering.value = it }
    }

    private fun setFilterByYearSpinnerSelection(filterByYearValue: String?) {
        val filterByYearValuePosition =
            yearsList.indexOfFirst { it == filterByYearValue }
        _filterByYearSpinnerSelection.value =
            if (filterByYearValuePosition != -1) filterByYearValuePosition else yearsList.lastIndex
    }

    private fun setSortBySpinnerSelection(context: Context, sortByValue: String?) {
        val sortByValuePosition =
            getSortByList(context).indexOfFirst { it == getSortFilterName(sortByValue, context) }
        if (sortByValuePosition != -1) {
            _sortBySpinnerSelection.value = sortByValuePosition
        }
    }

    fun updateFilters(filters: MovieFiltersModel) {
        viewModelScope.launch {
            val filtersGotUpdated = updateFiltersUseCase(filters)
            loadMovies(shouldReload = filtersGotUpdated)
        }
    }

    fun setSortByList(context: Context) {
        _sortByData.value = getSortByList(context)
    }

    fun setFilterByYearList(context: Context) {
        fillYearsList(context)
        _yearsData.value = yearsList
    }

    private fun getSortByList(context: Context) = SortFiltersEnum.getSortFilters(context)

    private fun fillYearsList(context: Context) {
        for (year in 1900..Calendar.getInstance().get(Calendar.YEAR)) {
            yearsList.add(year.toString())
        }
        yearsList.add(context.getString(R.string.all_years))
    }

    fun getYearsListDefaultValue(): Int {
        return yearsList.lastIndex
    }

    private fun getSortFilterName(filterId: String?, context: Context) =
        SortFiltersEnum.getSortFilterNameFromId(
            filterId = filterId,
            context = context
        )

    fun getGenres() {
        viewModelScope.launch {
            getGenresFromDBUseCase().collect { genresList ->
                _genreFiltersData.value = genresList
            }
        }
    }
}