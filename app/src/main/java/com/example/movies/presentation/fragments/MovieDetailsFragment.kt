package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieDetailsBinding
import com.example.movies.domain.Movie
import com.example.movies.presentation.MoviesViewModel
import com.example.movies.presentation.adapter.ActorCastAdapter
import com.example.movies.presentation.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    private val viewModel: MoviesViewModel by viewModels()
    private val adapter = ActorCastAdapter()
    override fun receiveView() = FragmentMovieDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie: Movie = requireArguments().getSerializable(MOVIE_DETAILS) as Movie
        viewModel.fetchActorsCast(movie.id)
        observe {
            viewModel.actors.collect {
                adapter.submitList(it)
            }
        }
        initViews(movie)
    }

    private fun initViews(movie: Movie) {
        binding.apply {
            castRecycler.adapter = adapter
            tvTitle.text = movie.title
            tvRealiseDate.text = getString(R.string.release_date, movie.releaseDate)
            tvOverview.text = movie.overview
            tvIsAdult.text = getString(R.string.is_adult, movie.adult.toString())
            fabFavorite.setOnClickListener {
                viewModel.addToFavorite(movie)
            }
            btnPlayTrailer.setOnClickListener {
                navigate().showTrailer(movie.id)
            }
            Glide
                .with(root)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .error(R.drawable.error)
                .into(ivPoster)
        }
    }

    companion object {

        private const val MOVIE_DETAILS = "movie_details"

        fun createArgs(movie: Movie) = bundleOf(MOVIE_DETAILS to movie)
    }
}