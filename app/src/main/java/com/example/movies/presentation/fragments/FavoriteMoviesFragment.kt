package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movies.databinding.FragmentFavoriteMoviesBinding
import com.example.movies.presentation.adapter.MovieAdapter
import com.example.movies.presentation.MoviesViewModel
import com.example.movies.presentation.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding>() {

    override fun receiveView() = FragmentFavoriteMoviesBinding.inflate(layoutInflater)

    private val viewModel: MoviesViewModel by viewModels()
    private val adapter = MovieAdapter { movie ->
        navigate().showMovieDetails(movie)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRecycler.adapter = adapter
        observe {
            viewModel.fetchFavoriteMovies().collect {
                adapter.submitList(it)
            }
        }
    }
}