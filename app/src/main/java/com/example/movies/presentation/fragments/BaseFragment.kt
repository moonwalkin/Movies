package com.example.movies.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.movies.domain.State
import kotlinx.coroutines.launch

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private var _binding: T? = null
    protected val binding: T get() = checkNotNull(_binding) { "Fragment == null" }
    abstract fun receiveView(): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = receiveView()

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected fun observe(body: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                body()
            }
        }
    }

    protected fun <T> renderState(
        root: ViewGroup,
        state: State<T>,
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit,
        onLoading: () -> Unit
    ) {
        root.children.forEach { it.visibility = View.GONE }
        when (state) {
            is State.Loading -> {
                onLoading()
            }
            is State.Success -> {
                onSuccess(state.data)
            }
            is State.Error -> {
                onError(state.message)
            }
        }
    }
}