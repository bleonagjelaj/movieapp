package com.frakton.moviesapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frakton.moviesapp.domain.models.MovieFiltersModel
import com.frakton.moviesapp.domain.usecases.GetFiltersInitialStateUseCase
import com.frakton.moviesapp.domain.usecases.UpdateFiltersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterMoviesViewModel @Inject constructor(
    private val getFiltersInitialStateUseCase: GetFiltersInitialStateUseCase,
    private val updateFiltersUseCase: UpdateFiltersUseCase
) : ViewModel() {
    private val _filtersData: MutableLiveData<MovieFiltersModel> = MutableLiveData()
    val filtersData = _filtersData

    fun getFilters() {
        viewModelScope.launch {
            getFiltersInitialStateUseCase().collect { movieFilters ->
                _filtersData.value = movieFilters
            }
        }
    }

    fun updateFilters(filters: MovieFiltersModel) {
        viewModelScope.launch {
            updateFiltersUseCase(filters)
        }
    }
}