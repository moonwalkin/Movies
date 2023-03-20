package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movies.databinding.FragmentNowPlayingMoviesBinding
import com.example.movies.presentation.MovieAdapter
import com.example.movies.presentation.MoviesViewModel
import com.example.movies.presentation.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NowPlayingMoviesFragment : Fragment() {
    private var _binding: FragmentNowPlayingMoviesBinding? = null
    private val binding: FragmentNowPlayingMoviesBinding get() = checkNotNull(_binding)
    private val viewModel: MoviesViewModel by viewModels()
    private val adapter = MovieAdapter {
        navigate().showMovieDetails(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNowPlayingMoviesBinding.inflate(layoutInflater)
        return binding.root
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}