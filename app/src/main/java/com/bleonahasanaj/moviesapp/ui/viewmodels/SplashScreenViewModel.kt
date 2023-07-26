package com.bleonahasanaj.moviesapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bleonahasanaj.moviesapp.domain.usecases.GetGenresUseCase
import com.bleonahasanaj.moviesapp.domain.usecases.UpdateGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val updateGenresUseCase: UpdateGenresUseCase
) : ViewModel() {
    private val _gotGenresFromApi = MutableLiveData<Boolean>()
    val gotGenresFromApi = _gotGenresFromApi

    fun getGenres() = viewModelScope.launch {
        try {
            getGenresUseCase().collect { genresList ->
                updateGenresUseCase(genresList)
            }
            _gotGenresFromApi.value = true
        } catch (exception: Exception) {
            _gotGenresFromApi.value = false
        }
    }
}