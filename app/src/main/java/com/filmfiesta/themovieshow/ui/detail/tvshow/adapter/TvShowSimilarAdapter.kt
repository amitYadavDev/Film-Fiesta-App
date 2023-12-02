package com.filmfiesta.themovieshow.ui.detail.tvshow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.filmfiesta.themovieshow.databinding.ItemSimilarBinding
import com.filmfiesta.themovieshow.model.response.tvshow.similar.TvShowsSimilarResultsItem
import com.filmfiesta.themovieshow.utils.CommonDateFormatConstants
import com.filmfiesta.themovieshow.utils.convertDate
import com.filmfiesta.themovieshow.utils.loadImage

class TvShowSimilarAdapter(
    private val onItemClickListener: (TvShowsSimilarResultsItem) -> Unit
) : ListAdapter<TvShowsSimilarResultsItem, TvShowSimilarAdapter.TvShowViewHolder>(
    TV_SHOW_SIMILAR_DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(
            ItemSimilarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TvShowViewHolder(private val binding: ItemSimilarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvShowsSimilarResultsItem) {
            binding.apply {
                imgPoster.loadImage(data.posterPath)
                val releaseYear = data.firstAirDate.convertDate(
                    CommonDateFormatConstants.YYYY_MM_DD_FORMAT,
                    CommonDateFormatConstants.YYYY_FORMAT
                )
                tvMovieName.text = StringBuilder(data.name).append("\n").append("($releaseYear)")
                cardPoster.setOnClickListener {
                    onItemClickListener(data)
                }
            }
        }
    }

    companion object {
        val TV_SHOW_SIMILAR_DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<TvShowsSimilarResultsItem>() {
            override fun areItemsTheSame(
                oldItem: TvShowsSimilarResultsItem,
                newItem: TvShowsSimilarResultsItem
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: TvShowsSimilarResultsItem,
                newItem: TvShowsSimilarResultsItem
            ): Boolean = oldItem == newItem
        }
    }
}