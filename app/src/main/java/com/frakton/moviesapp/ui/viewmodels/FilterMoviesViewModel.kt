package com.frakton.moviesapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frakton.moviesapp.data.retrofit.models.request.MovieFilters
import com.frakton.moviesapp.domain.usecases.GetFiltersUseCase
import com.frakton.moviesapp.domain.usecases.UpdateFiltersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterMoviesViewModel @Inject constructor(
    private val getFiltersUseCase: GetFiltersUseCase,
    private val updateFiltersUseCase: UpdateFiltersUseCase
) : ViewModel() {
    private val _filtersData: MutableLiveData<MovieFilters> = MutableLiveData()
    val filtersData = _filtersData

    fun getFilters() {
        viewModelScope.launch {
            getFiltersUseCase().collect { movieFilters ->
                _filtersData.value = movieFilters
            }
        }
    }

    fun updateFilters(filters: MovieFilters) {
        viewModelScope.launch {
            updateFiltersUseCase(filters)
        }
    }
}