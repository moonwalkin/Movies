package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.FragmentFavoriteMoviesBinding
import com.example.movies.databinding.LayoutResultBinding
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
        swipeToDelete()
        observe()
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
                        adapter.submitList(it)
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
            viewModel.fetchFavoriteMovies()
        }
    }

    private fun swipeToDelete() {
        binding.movieRecycler.adapter = adapter
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteFromFavorite(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.movieRecycler)
    }
}