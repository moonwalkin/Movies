package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movies.databinding.FragmentMovieDetailsBinding
import com.example.movies.domain.Movie
import com.example.movies.presentation.MoviesViewModel
import com.example.movies.presentation.adapter.ActorCastAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    private val viewModel: MoviesViewModel by viewModels()
    private val adapter = ActorCastAdapter()
    override fun receiveView() = FragmentMovieDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie: Movie = requireArguments().getSerializable(MOVIE_DETAILS) as Movie
        binding.castRecycler.adapter = adapter
        viewModel.fetchActorsCast(movie.id)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.actors.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    companion object {
        private const val MOVIE_DETAILS = "movie_details"

        fun createArgs(movie: Movie) = bundleOf(MOVIE_DETAILS to movie)
    }
}