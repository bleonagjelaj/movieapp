package com.frakton.moviesapp.activities

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.frakton.moviesapp.adapters.MoviesViewPagerAdapter
import com.frakton.moviesapp.databinding.ActivityMainBinding
import com.frakton.moviesapp.viewmodels.MoviesViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        lifecycleScope.launch{
            viewModel.loadMovies()?.observe(this@MainActivity) {
                val adapter = MoviesViewPagerAdapter()
                binding.movieViewPager.adapter = adapter
                adapter.setData(it)
            }
        }
    }
}