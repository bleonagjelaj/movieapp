package com.frakton.moviesapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.TrailerVideoItemBinding
import com.frakton.moviesapp.domain.callbacks.TrailerItemClickCallback
import com.frakton.moviesapp.domain.models.TrailerDetails
import com.squareup.picasso.Picasso

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
            Picasso.get()
                .load(movieTrailer.thumbnailPath)
                .fit()
                .error(R.drawable.ic_image_not_supported)
                .into(trailersBinding.videoThumbnail)
        }

        override fun onClick(v: View?) {
            val trailerItem = movieTrailersList[bindingAdapterPosition]
            if (trailerItem != null) {
                trailerItemClickCallback.onTrailerItemClicked(trailerItem.key)
            }
        }
    }
}