package com.frakton.moviesapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.MovieItemBinding
import com.frakton.moviesapp.domain.models.MovieModel
import com.squareup.picasso.Picasso

class MoviesViewPagerAdapter :
    PagingDataAdapter<MovieModel, MoviesViewPagerAdapter.MoviesViewPagerHolder>(MovieComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewPagerHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewPagerHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class MoviesViewPagerHolder(private val movieItemBinding: MovieItemBinding) :
        ViewHolder(movieItemBinding.root) {
        fun bind(movie: MovieModel) {
            with(movieItemBinding){
                moviePublishDateText.text = movie.movieReleaseDate
                setMovieCoverImage(movieCoverImage, movie.moviePosterPath)
                movieRating.rating = movie.movieRating
                movieGenreText.text = movie.movieGenres
            }
        }

        private fun setMovieCoverImage(movieCoverImage: ImageView, posterPath: String) {
            Picasso.get()
                .load(posterPath)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image_not_supported)
                .into(movieCoverImage)
        }
    }

    object MovieComparator : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }
}