package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.movies.databinding.FragmentPopularMoviesBinding
import com.example.movies.databinding.LayoutResultBinding
import com.example.movies.presentation.adapter.movie.MovieAdapter
import com.example.movies.presentation.navigate
import com.example.movies.presentation.viewmodels.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : BaseFragment<FragmentPopularMoviesBinding>() {

    override fun receiveView() = FragmentPopularMoviesBinding.inflate(layoutInflater)

    private val viewModel: PopularMoviesViewModel by viewModels()

    private val movieAdapter = MovieAdapter { movie ->
        navigate().showMovieDetails(movie)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRecycler.adapter = movieAdapter
        movieAdapter.shouldLoadMore = {
            viewModel.fetchPopularMovies()
        }
        observe()
    }

    private fun observe() {
        val resultBinding = LayoutResultBinding.bind(binding.root)
        observe {
            viewModel.items.collect { state ->
                renderState(
                    root = binding.root,
                    state = state,
                    onSuccess = {
                        binding.movieRecycler.isVisible = true
                        movieAdapter.submitList(it)
                    },
                    onError = {
                        resultBinding.errorContainer.isVisible = true
                        resultBinding.tvErrorMessage.text = it
                    },
                    onLoading = {
                        resultBinding.progressBar.isVisible = true
                    }
                )
            }
        }
        resultBinding.btnRetry.setOnClickListener {
            viewModel.fetchPopularMovies()
        }
    }
}