package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movies.databinding.FragmentFavoriteMoviesBinding
import com.example.movies.presentation.adapter.MovieAdapter
import com.example.movies.presentation.navigate
import com.example.movies.presentation.viewmodels.FavoriteMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding>() {

    override fun receiveView() = FragmentFavoriteMoviesBinding.inflate(layoutInflater)

    private val viewModel: FavoriteMoviesViewModel by viewModels()
    private val adapter = MovieAdapter { movie ->
        navigate().showMovieDetails(movie)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRecycler.adapter = adapter
        observe {
            viewModel.items.collect {
                adapter.submitList(it)
            }
        }
    }
}