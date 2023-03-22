package com.example.movies.presentation

import androidx.fragment.app.Fragment
import com.example.movies.domain.entity.Movie

interface Navigator {
    fun showMovieDetails(movie: Movie)
    fun close()
    fun showTrailer(id: Int)
}

fun Fragment.navigate(): Navigator {
    return requireActivity() as Navigator
}