package com.example.movies.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.data.Movie
import com.example.movies.databinding.LayoutMovieBinding

class MovieViewHolder(private val binding: LayoutMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        binding.apply {
            tvTitle.text = movie.original_title
            tvRating.text = movie.vote_average.toString()

            Glide
                .with(root)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .into(ivPoster)
        }
    }

}