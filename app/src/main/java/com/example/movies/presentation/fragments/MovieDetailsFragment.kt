package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieDetailsBinding
import com.example.movies.databinding.LayoutResultBinding
import com.example.movies.domain.entity.Movie
import com.example.movies.presentation.viewmodels.MoviesViewModel
import com.example.movies.presentation.adapter.ActorCastAdapter
import com.example.movies.presentation.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    private val args by navArgs<MovieDetailsFragmentArgs>()
    private val viewModel: MoviesViewModel by viewModels()
    private val adapter = ActorCastAdapter()
    override fun receiveView() = FragmentMovieDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = args.movie
        viewModel.fetchActorsCast(movie.id)
        observe()
        initViews(movie)
    }

    private fun observe() {
        val resultBinding = LayoutResultBinding.bind(binding.root)
        observe {
            viewModel.items.collect { state ->
                renderState(
                    root = binding.root,
                    state = state,
                    onSuccess = {
                        setVisibilityView(true)
                        adapter.submitList(it)
                    },
                    onError = {
                        resultBinding.errorContainer.visibility = View.VISIBLE
                        resultBinding.tvErrorMessage.text = it
                    },
                    onLoading = {
                        resultBinding.progressBar.visibility = View.VISIBLE
                    }
                )
            }
        }
    }

    private fun setVisibilityView(isVisible: Boolean) {
        binding.apply {
            castRecycler.isVisible = isVisible
            fabFavorite.isVisible = isVisible
            tvIsAdult.isVisible = isVisible
            tvOverview.isVisible = isVisible
            ivPoster.isVisible = isVisible
            tvTitle.isVisible = isVisible
            scrollView.isVisible = isVisible
            tvRealiseDate.isVisible = isVisible
            btnPlayTrailer.isVisible = isVisible
        }
    }

    private fun initViews(movie: Movie) {
        binding.apply {
            castRecycler.adapter = adapter
            tvTitle.text = movie.title
            tvRealiseDate.text = getString(R.string.release_date, movie.releaseDate)
            tvOverview.text = movie.overview
            tvIsAdult.text = getString(R.string.is_adult, movie.adult.toString())
            fabFavorite.setOnClickListener {
                viewModel.addToFavorite(movie)
            }
            btnPlayTrailer.setOnClickListener {
                navigate().showTrailer(movie.id)
            }
            Glide
                .with(root)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .error(R.drawable.error)
                .into(ivPoster)
        }
    }

    companion object {

        private const val MOVIE_DETAILS = "movie_details"

        fun createArgs(movie: Movie) = bundleOf(MOVIE_DETAILS to movie)
    }
}