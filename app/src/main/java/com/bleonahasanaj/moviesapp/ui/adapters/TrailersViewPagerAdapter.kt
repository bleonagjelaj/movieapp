package com.bleonahasanaj.moviesapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bleonahasanaj.moviesapp.databinding.TrailerVideoItemBinding
import com.bleonahasanaj.moviesapp.domain.callbacks.TrailerItemClickCallback
import com.bleonahasanaj.moviesapp.domain.models.TrailerDetails
import com.bleonahasanaj.moviesapp.util.loadAndFitImage

class TrailersViewPagerAdapter(private val trailerItemClickCallback: TrailerItemClickCallback) :
    RecyclerView.Adapter<TrailersViewPagerAdapter.MovieTrailersViewHolder>() {
    private var movieTrailersList = listOf<TrailerDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTrailersViewHolder {
        val binding =
            TrailerVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieTrailersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieTrailersViewHolder, position: Int) {
        holder.bind(movieTrailersList[position])
    }

    override fun getItemCount(): Int = movieTrailersList.size

    fun setData(trailerVideoIds: List<TrailerDetails>) {
        movieTrailersList = trailerVideoIds
        notifyDataSetChanged()
    }

    inner class MovieTrailersViewHolder(private val trailersBinding: TrailerVideoItemBinding) :
        ViewHolder(trailersBinding.root), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movieTrailer: TrailerDetails) {
            trailersBinding.videoThumbnail.loadAndFitImage(imagePath = movieTrailer.thumbnailPath)
        }

        override fun onClick(v: View?) {
            val trailerItem = movieTrailersList[bindingAdapterPosition]
            trailerItemClickCallback.onTrailerItemClicked(trailerKey = trailerItem.key)
        }
    }
}