package com.filmfiesta.themovieshow.ui.movie.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.filmfiesta.themovieshow.R
import com.filmfiesta.themovieshow.data.NetworkResult
import com.filmfiesta.themovieshow.databinding.FragmentMovieTvShowsListBinding
import com.filmfiesta.themovieshow.ui.favorite.viewmodel.FavoriteMovieViewModel
import com.filmfiesta.themovieshow.ui.movie.adapter.MovieTrendingAdapter
import com.filmfiesta.themovieshow.ui.movie.viewmodel.MovieViewModel
import com.filmfiesta.themovieshow.utils.navigateToDetailMovie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieTrendingFragment : Fragment() {

    private var _binding: FragmentMovieTvShowsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MovieViewModel>()
    private var movieTrendingAdapter: MovieTrendingAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieTvShowsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        setMovieTrendingObserver()
    }

    private fun initAdapter() {
        movieTrendingAdapter = MovieTrendingAdapter {
            navigateToDetailMovie(it.id, it.title, FavoriteMovieViewModel.position)
        }

        binding.rvCommon.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = movieTrendingAdapter
        }
    }

    private fun setMovieTrendingObserver() {
        viewModel.getMovieTrendingDay.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> showLoading(true)
                is NetworkResult.Success -> {
                    showLoading(false)
                    movieTrendingAdapter?.submitList(it.data?.results?.toMutableList())
                }
                is NetworkResult.Error -> {
                    showLoading(false)
                    showError(it.message)
                }
                else -> {
                    showLoading(false)
                    showError(getString(R.string.tvOops))
                }
            }
        }
    }

    private fun showLoading(isShow: Boolean) {
        binding.pbLoading.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    private fun showError(message: String?) {
        binding.layoutError.apply {
            root.isVisible = true
            tvErrorDesc.text = message
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        movieTrendingAdapter = null
    }
}