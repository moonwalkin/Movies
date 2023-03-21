package com.example.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.Cast
import com.example.movies.databinding.LayoutActorBinding

class ActorCastAdapter : ListAdapter<Cast, CastViewHolder>(ActorDifUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            LayoutActorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

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

object ActorDifUtil : DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem == newItem
    }
}