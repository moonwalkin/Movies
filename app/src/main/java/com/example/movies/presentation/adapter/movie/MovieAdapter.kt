package com.example.movies.presentation.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.movies.databinding.LayoutMovieBinding
import com.example.movies.domain.entity.Movie

class MovieAdapter(
    private val listener: (Movie) -> Unit
) : ListAdapter<Movie, MovieViewHolder>(
    MovieDifUtil
) {

    var shouldLoadMore: () -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
        if (currentList.size - GAP in (GAP + 1) until position) {
            shouldLoadMore()
        }
    }

    companion object {
        private const val GAP = 3
    }
}