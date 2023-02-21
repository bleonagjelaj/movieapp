package com.frakton.moviesapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.frakton.moviesapp.databinding.GenreFilterItemBinding
import com.frakton.moviesapp.domain.enums.MovieGenreEnum
import com.frakton.moviesapp.domain.models.GenreFilterModel
import com.frakton.moviesapp.util.gone
import com.frakton.moviesapp.util.visible

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

    fun updateStatus(genresValue: List<Int>) {
        genresList.forEach {
            it.isChecked = it.id in genresValue
        }
    }

    fun getCheckedGenres(): List<Int> {
        val checkedGenres = arrayListOf<Int>()
        genresList.filter { it.isChecked }.forEach { checkedGenre ->
            checkedGenres.add(checkedGenre.id)
        }
        return checkedGenres
    }

    inner class GenresFiltersViewHolder(private val genreFilterItemBinding: GenreFilterItemBinding) :
        ViewHolder(genreFilterItemBinding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(genreFilter: GenreFilterModel) {
            with(genreFilterItemBinding) {
                genreFilterTitle.text = genreFilter.name
                genreFilterIcon.setImageDrawable(
                    itemView.context.getDrawable(genreFilter.icon)
                )
                if (genreFilter.isChecked) {
                    checkmark.visible()
                } else {
                    checkmark.gone()
                }
            }

        }

        override fun onClick(v: View?) {
            toggleCheckmark()
        }

        private fun toggleCheckmark() {
            genresList[bindingAdapterPosition].isChecked =
                !genresList[bindingAdapterPosition].isChecked
            with(genreFilterItemBinding.checkmark) {
                if (genresList[bindingAdapterPosition].isChecked) visible() else gone()
            }
        }
    }
}