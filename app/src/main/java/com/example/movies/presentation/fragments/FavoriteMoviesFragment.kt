package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movies.databinding.FragmentFavoriteMoviesBinding
import com.example.movies.presentation.MovieAdapter
import com.example.movies.presentation.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding>() {

    override fun receiveView() = FragmentFavoriteMoviesBinding.inflate(layoutInflater)

    private val viewModel: MoviesViewModel by viewModels()
    private val adapter = MovieAdapter { movie ->

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRecycler.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchFavoriteMovies().collect {
                    adapter.submitList(it)
                }
            }
        }
    }
}