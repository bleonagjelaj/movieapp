package com.frakton.moviesapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.frakton.moviesapp.ui.viewmodels.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getGenres()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.gotGenresFromApi.observe(this) { gotGenresFromApi ->
            if(gotGenresFromApi) {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                finish()
            } else {
                viewModel.getGenres()
            }
        }
    }
}