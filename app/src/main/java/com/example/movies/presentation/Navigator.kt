package com.example.movies.presentation

import androidx.fragment.app.Fragment
import com.example.movies.domain.Movie

interface Navigator {
    fun showMovieDetails(movie: Movie)
    fun close()
    fun showTrailer(id: Int)
    fun showActorDetails(id: Int)
}

fun Fragment.navigate(): Navigator {
    return requireActivity() as Navigator
}