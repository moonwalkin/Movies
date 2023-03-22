package com.example.movies.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movies.domain.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel<T> : ViewModel() {
    protected val _items = MutableStateFlow<State<T>>(State.Loading())
    val items = _items.asStateFlow()
}