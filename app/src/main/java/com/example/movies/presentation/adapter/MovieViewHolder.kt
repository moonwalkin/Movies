package com.example.movies.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.LayoutMovieBinding
import com.example.movies.domain.Movie

class MovieViewHolder(private val binding: LayoutMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie, listener: (Movie) -> Unit) {
        binding.apply {
            tvTitle.text = movie.original_title
            tvRating.text = movie.vote_average.toString()

            Glide
                .with(root)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .error(R.drawable.error)
                .into(ivPoster)
            root.setOnClickListener {
                listener(movie)
            }
        }
    }

}