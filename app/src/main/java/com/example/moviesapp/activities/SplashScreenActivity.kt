package com.example.moviesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.moviesapp.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private var startMainActivityJob: Job? = null
    private val startMainActivityDelay = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMainActivityStartJob()
    }

    private fun initMainActivityStartJob() {
        cancelJob()
        startMainActivityJob = lifecycleScope.launch {
            delay(startMainActivityDelay)
            val i = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun cancelJob() {
        startMainActivityJob?.cancel()
    }
}