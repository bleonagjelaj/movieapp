package com.frakton.moviesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.MovieItemBinding
import com.frakton.moviesapp.enums.MovieGenreEnum
import com.frakton.moviesapp.models.Movie
import com.frakton.moviesapp.utils.Constants
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class MoviesViewPagerAdapter :
    PagingDataAdapter<Movie, MoviesViewPagerAdapter.MoviesViewPagerHolder>(MovieComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewPagerHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewPagerHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class MoviesViewPagerHolder(private val movieItemBinding: MovieItemBinding) :
        ViewHolder(movieItemBinding.root) {
        fun bind(movie: Movie) {
            movieItemBinding.moviePublishDateText.text = movie.releaseDate?.let { formatDate(it) }
            setMovieCoverImage(
                movieItemBinding.movieCoverImage,
                movie.posterPath ?: movie.backdropPath ?: ""
            )
            movieItemBinding.movieRating.rating = (movie.voteAverage ?: 0F) / 2F
            movieItemBinding.movieGenreText.text = getGenres(movie.genreIds)
        }

        private fun getGenres(genreIds: List<Int>?): String {
            var genresString = ""
            genreIds?.forEachIndexed { i: Int, genreId: Int ->
                genresString += MovieGenreEnum.getGenreById(genreId)
                if (genreIds.lastIndex != i) {
                    genresString += " | "
                }
            }
            return genresString
        }

        private fun setMovieCoverImage(movieCoverImage: ImageView, posterPath: String) {
            val posterUrl = "${Constants.MOVIES_IMAGE_URL}${posterPath}"
            Picasso.get()
                .load(posterUrl)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image_not_supported)
                .into(movieCoverImage)
        }

        private fun formatDate(releaseDate: String): String {
            val date = SimpleDateFormat("yyyy-mm-dd").parse(releaseDate)
            return SimpleDateFormat("MMM yyyy").format(date)
        }
    }

    object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.originalTitle == newItem.originalTitle
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}