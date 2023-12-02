package com.filmfiesta.themovieshow.ui.tvshow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.filmfiesta.themovieshow.R
import com.filmfiesta.themovieshow.databinding.ItemsMoviesTvShowBinding
import com.filmfiesta.themovieshow.model.response.tvshow.discover.DiscoverTvShowsResult
import com.filmfiesta.themovieshow.utils.CommonDateFormatConstants.EEE_D_MMM_YYYY_FORMAT
import com.filmfiesta.themovieshow.utils.CommonDateFormatConstants.YYYY_MM_DD_FORMAT
import com.filmfiesta.themovieshow.utils.convertDate
import com.filmfiesta.themovieshow.utils.loadImage
import com.filmfiesta.themovieshow.utils.toRating

class DiscoverTvShowsAdapter(
    private val onItemClickListener: (DiscoverTvShowsResult) -> Unit
) : ListAdapter<DiscoverTvShowsResult, DiscoverTvShowsAdapter.TvShowsViewHolder>(
    DISCOVER_TV_SHOW_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemsMovieShowBinding = ItemsMoviesTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemsMovieShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TvShowsViewHolder(private val binding: ItemsMoviesTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DiscoverTvShowsResult) {
            binding.apply {
                imgPoster.loadImage(data.posterPath)
                tvTitle.text = data.name
                tvReleaseDate.text =
                    data.firstAirDate?.convertDate(YYYY_MM_DD_FORMAT, EEE_D_MMM_YYYY_FORMAT)
                        ?: root.context.getString(R.string.tvNA)
                tvRating.text = toRating(data.voteAverage)

                cardMoviesTvShow.setOnClickListener {
                    onItemClickListener(data)
                }
            }
        }
    }

    companion object {
        val DISCOVER_TV_SHOW_CALLBACK = object :
            DiffUtil.ItemCallback<DiscoverTvShowsResult>() {
            override fun areItemsTheSame(
                oldItem: DiscoverTvShowsResult,
                newItem: DiscoverTvShowsResult
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: DiscoverTvShowsResult,
                newItem: DiscoverTvShowsResult
            ): Boolean = oldItem == newItem
        }
    }
}