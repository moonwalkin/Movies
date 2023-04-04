package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.movies.databinding.FragmentNowPlayingMoviesBinding
import com.example.movies.databinding.LayoutResultBinding
import com.example.movies.presentation.adapter.MovieAdapter
import com.example.movies.presentation.navigate
import com.example.movies.presentation.viewmodels.NowPlayingMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NowPlayingMoviesFragment : BaseFragment<FragmentNowPlayingMoviesBinding>() {

    override fun receiveView() = FragmentNowPlayingMoviesBinding.inflate(layoutInflater)

    private val viewModel: NowPlayingMoviesViewModel by viewModels()

    private val adapter = MovieAdapter {
        navigate().showMovieDetails(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRecycler.adapter = adapter
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
                        adapter.submitList(it)
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
            viewModel.fetchNowPlayingMovies()
        }
    }
}

