package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movies.databinding.FragmentTrailerBinding
import com.example.movies.presentation.MoviesViewModel
import com.example.movies.presentation.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrailerFragment : BaseFragment<FragmentTrailerBinding>() {

    override fun receiveView() = FragmentTrailerBinding.inflate(layoutInflater)

    private val viewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(MOVIE_ID)
        viewModel.fetchMovieTrailer(id)
        binding.webView.apply {
            webChromeClient = WebChromeClient()
            observe {
                viewModel.trailerId.collect {
                    loadUrl("https://www.youtube.com/watch?v=$it")
                }
            }
        }

        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigate().close()
            }
        }
    }



    companion object {
        private const val MOVIE_ID = "id"

        fun createArgs(id: Int) = bundleOf(MOVIE_ID to id)
    }
}