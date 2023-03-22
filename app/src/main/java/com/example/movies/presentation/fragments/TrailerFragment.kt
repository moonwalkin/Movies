package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movies.databinding.FragmentTrailerBinding
import com.example.movies.databinding.LayoutResultBinding
import com.example.movies.presentation.navigate
import com.example.movies.presentation.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrailerFragment : BaseFragment<FragmentTrailerBinding>() {

    override fun receiveView() = FragmentTrailerBinding.inflate(layoutInflater)

    private val args by navArgs<TrailerFragmentArgs>()

    private val viewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMovieTrailer(args.trailerId)
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
                        navigate().close()
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
}