package com.example.movies.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.movies.data.Movie
import com.example.movies.databinding.LayoutMovieBinding

class MovieViewHolder(private val binding: LayoutMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        binding.textView.text = movie.title
    }
}