package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movies.databinding.FragmentNowPlayingMoviesBinding
import com.example.movies.presentation.adapter.MovieAdapter
import com.example.movies.presentation.MoviesViewModel
import com.example.movies.presentation.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NowPlayingMoviesFragment : BaseFragment<FragmentNowPlayingMoviesBinding>() {

    override fun receiveView() = FragmentNowPlayingMoviesBinding.inflate(layoutInflater)

    private val viewModel: MoviesViewModel by viewModels()
    private val adapter = MovieAdapter {
        navigate().showMovieDetails(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRecycler.adapter = adapter
        viewModel.fetchNowPlayingMovies()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.nowPlayingMovies.collect {
                    adapter.submitList(it)
                }
            }
        }
    }
}