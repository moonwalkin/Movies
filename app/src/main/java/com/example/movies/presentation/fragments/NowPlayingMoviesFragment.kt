package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movies.databinding.FragmentNowPlayingMoviesBinding
import com.example.movies.presentation.MovieAdapter
import com.example.movies.presentation.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NowPlayingMoviesFragment : BaseFragment<FragmentNowPlayingMoviesBinding>() {

    override fun receiveView() = FragmentNowPlayingMoviesBinding.inflate(layoutInflater)

    private val viewModel: MoviesViewModel by viewModels()
    private val adapter = MovieAdapter {
        viewModel.addToFavorite(it)
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