package com.frakton.moviesapp.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.frakton.moviesapp.databinding.MovieItemBinding
import com.frakton.moviesapp.models.Movie
import java.time.LocalDate

class MoviesViewPagerAdapter : RecyclerView.Adapter<MoviesViewPagerAdapter.MoviesViewPagerHolder>() {
    var moviesList = ArrayList<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewPagerHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewPagerHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int = moviesList.count()

    fun setData(movies: List<Movie>?) {
        moviesList = movies as ArrayList<Movie>
    }

    inner class MoviesViewPagerHolder(private val movieItemBinding: MovieItemBinding) :
        ViewHolder(movieItemBinding.root) {
        fun bind(movie: Movie) {
            movieItemBinding.moviePublishDateText.text = movie.releaseDate?.let { formatDate(it) }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun formatDate(releaseDate: String): String {
            val date = LocalDate.parse(releaseDate)
            return "${date.year} ${date.month}"
        }
    }
}