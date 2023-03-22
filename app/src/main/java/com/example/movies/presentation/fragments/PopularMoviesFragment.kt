package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.FragmentPopularMoviesBinding
import com.example.movies.databinding.LayoutResultBinding
import com.example.movies.presentation.adapter.MovieAdapter
import com.example.movies.presentation.navigate
import com.example.movies.presentation.viewmodels.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : BaseFragment<FragmentPopularMoviesBinding>() {

    override fun receiveView() = FragmentPopularMoviesBinding.inflate(layoutInflater)

    private val viewModel: PopularMoviesViewModel by viewModels()

    private val movieAdapter = MovieAdapter { movie ->
        navigate().showMovieDetails(movie)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(context, 2)
        setupRecyclerView(layoutManager)
        viewModel.fetchPopularMovies()
        observe()
    }

    private fun setupRecyclerView(layoutManager: GridLayoutManager) {
        with(binding.movieRecycler) {
            adapter = movieAdapter
            this.layoutManager = layoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        viewModel.fetchPopularMovies()
                    }
                }
            })
        }
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
                        movieAdapter.submitList(it)
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
            viewModel.fetchPopularMovies()
        }
    }
}