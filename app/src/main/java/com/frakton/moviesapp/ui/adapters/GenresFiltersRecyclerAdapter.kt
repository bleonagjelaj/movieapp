package com.frakton.moviesapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.frakton.moviesapp.databinding.GenreFilterItemBinding
import com.frakton.moviesapp.domain.enums.MovieGenreEnum

class GenresFiltersRecyclerAdapter :
    RecyclerView.Adapter<GenresFiltersRecyclerAdapter.GenresFiltersViewHolder>() {

    val genresList = MovieGenreEnum.getGenresFilters()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresFiltersViewHolder {
        val binding =
            GenreFilterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenresFiltersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenresFiltersViewHolder, position: Int) {
        holder.bind(genresList[position])
    }

    override fun getItemCount() = genresList.size

    inner class GenresFiltersViewHolder(private val genreFilterItemBinding: GenreFilterItemBinding) :
        ViewHolder(genreFilterItemBinding.root) {
        fun bind(movieGenreEnum: MovieGenreEnum) {
            genreFilterItemBinding.genreFilterTitle.text = movieGenreEnum.genre
            genreFilterItemBinding.genreFilterIcon.setImageDrawable(
                itemView.context.getDrawable(movieGenreEnum.icon)
            )
        }
    }
}