package com.filmfiesta.themovieshow.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.filmfiesta.themovieshow.R
import com.filmfiesta.themovieshow.databinding.ItemsMoviesTvShowBinding
import com.filmfiesta.themovieshow.model.response.movie.discover.DiscoverMovieResult
import com.filmfiesta.themovieshow.utils.CommonDateFormatConstants
import com.filmfiesta.themovieshow.utils.convertDate
import com.filmfiesta.themovieshow.utils.loadImage
import com.filmfiesta.themovieshow.utils.toRating

class DiscoverMovieAdapter(
    private val onItemClickListener: (DiscoverMovieResult) -> Unit
) : ListAdapter<DiscoverMovieResult, DiscoverMovieAdapter.MovieViewHolder>(
    DISCOVER_MOVIE_DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemsMoviesTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(private val binding: ItemsMoviesTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DiscoverMovieResult) {
            binding.apply {
                imgPoster.loadImage(data.posterPath)
                tvTitle.text = data.title
                tvReleaseDate.text = data.releaseDate?.convertDate(
                    CommonDateFormatConstants.YYYY_MM_DD_FORMAT,
                    CommonDateFormatConstants.EEE_D_MMM_YYYY_FORMAT
                ) ?: root.context.getString(R.string.tvNA)
                tvRating.text = toRating(data.voteAverage)

                cardMoviesTvShow.setOnClickListener {
                    onItemClickListener(data)
                }
            }
        }
    }

    companion object {
        val DISCOVER_MOVIE_DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<DiscoverMovieResult>() {
            override fun areItemsTheSame(
                oldItem: DiscoverMovieResult,
                newItem: DiscoverMovieResult
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: DiscoverMovieResult,
                newItem: DiscoverMovieResult
            ): Boolean = oldItem == newItem
        }
    }
}