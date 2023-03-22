package com.example.movies.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movies.domain.State
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

open class BaseViewModel<T> : ViewModel() {
    protected val _items = MutableSharedFlow<State<T>>()
    val items = _items.asSharedFlow()
}