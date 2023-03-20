package com.example.movies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.domain.Movie
import com.example.movies.presentation.fragments.MovieDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private val navController: NavController by lazy {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navHost.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun showMovieDetails(movie: Movie) {
        launch(R.id.fromMovieToDetails, MovieDetailsFragment.createArgs(movie))
    }

    private fun launch(destination: Int, args: Bundle? = null) {
        navController.navigate(destination, args)
    }
}

