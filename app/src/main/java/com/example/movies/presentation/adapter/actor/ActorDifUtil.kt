package com.example.movies.presentation.adapter.actor

import androidx.recyclerview.widget.DiffUtil
import com.example.movies.domain.entity.Cast

object ActorDifUtil : DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem == newItem
    }
}