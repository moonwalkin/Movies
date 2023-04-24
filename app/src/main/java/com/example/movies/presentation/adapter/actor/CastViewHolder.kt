package com.example.movies.presentation.adapter.actor

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.LayoutActorBinding
import com.example.movies.domain.entity.Cast

class CastViewHolder(private val binding: LayoutActorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(cast: Cast) {
        binding.tvName.text = cast.name
        Glide
            .with(binding.root)
            .load("https://image.tmdb.org/t/p/w500${cast.profilePath}")
            .error(R.drawable.error)
            .into(binding.ivPerson)
    }
}