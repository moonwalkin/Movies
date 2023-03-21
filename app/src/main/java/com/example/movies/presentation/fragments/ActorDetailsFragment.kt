package com.example.movies.presentation.fragments

import androidx.core.os.bundleOf
import com.example.movies.databinding.FragmentActorDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorDetailsFragment : BaseFragment<FragmentActorDetailsBinding>() {
    override fun receiveView() = FragmentActorDetailsBinding.inflate(layoutInflater)


    companion object {
        private const val ACTOR_ID = "id"
        fun createArgs(id: Int) = bundleOf(ACTOR_ID to id)
    }
}