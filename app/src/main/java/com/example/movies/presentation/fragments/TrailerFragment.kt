package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.movies.databinding.FragmentTrailerBinding
import com.example.movies.databinding.LayoutResultBinding
import com.example.movies.presentation.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

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
            observe(this)
        }
    }

    private fun observe(webView: WebView) {
        val resultBinding = LayoutResultBinding.bind(binding.root)
        observe {
            viewModel.trailerId.collect { state ->
                renderState(
                    root = binding.root,
                    state = state,
                    onSuccess = {
                        webView.loadUrl("https://www.youtube.com/watch?v=$it")
                    },
                    onError = {
                        resultBinding.errorContainer.isVisible = true
                        resultBinding.tvErrorMessage.text = it
                    },
                    onLoading = {
                        resultBinding.progressBar.isVisible = true
                    }
                )
            }
        }
    }

    companion object {
        private const val MOVIE_ID = "id"

        fun createArgs(id: Int) = bundleOf(MOVIE_ID to id)
    }
}