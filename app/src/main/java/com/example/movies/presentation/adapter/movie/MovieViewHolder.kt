package com.example.movies.presentation.adapter.movie

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.LayoutMovieBinding
import com.example.movies.domain.entity.Movie

class MovieViewHolder(private val binding: LayoutMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie, listener: (Movie) -> Unit) {
        binding.apply {
            tvTitle.text = movie.title
            tvRating.text = movie.voteAverage.toString()

            Glide
                .with(root)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .error(R.drawable.error)
                .into(ivPoster)
            root.setOnClickListener {
                listener(movie)
            }
        }
    }

}