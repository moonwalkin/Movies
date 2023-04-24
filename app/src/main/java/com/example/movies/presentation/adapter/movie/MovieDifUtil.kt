package com.example.movies.presentation.adapter.movie

import androidx.recyclerview.widget.DiffUtil
import com.example.movies.domain.entity.Movie

object MovieDifUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}