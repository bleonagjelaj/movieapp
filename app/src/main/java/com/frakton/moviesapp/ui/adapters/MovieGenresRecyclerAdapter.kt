package com.frakton.moviesapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frakton.moviesapp.databinding.GenreItemBinding

class MovieGenresRecyclerAdapter :
    RecyclerView.Adapter<MovieGenresRecyclerAdapter.MovieGenresViewHolder>() {
    private var genresList = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenresViewHolder {
        val binding = GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieGenresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieGenresViewHolder, position: Int) {
        holder.bind(genresList[position])
    }

    override fun getItemCount() = genresList.size

    fun setData(genres: List<String>) {
        genresList = genres
    }

    inner class MovieGenresViewHolder(private val genreBinding: GenreItemBinding) :
        RecyclerView.ViewHolder(genreBinding.root) {
        fun bind(genre: String) {
            genreBinding.genre.text = genre
        }
    }
}