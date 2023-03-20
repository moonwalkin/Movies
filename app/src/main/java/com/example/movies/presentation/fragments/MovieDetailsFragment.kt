package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.movies.databinding.FragmentMovieDetailsBinding
import com.example.movies.domain.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding: FragmentMovieDetailsBinding
        get() = checkNotNull(_binding)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie: Movie = requireArguments().getSerializable(MOVIE_DETAILS) as Movie


    }

    companion object {
        private const val MOVIE_DETAILS = "movie_details"

        fun createArgs(movie: Movie) = bundleOf(MOVIE_DETAILS to movie)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}