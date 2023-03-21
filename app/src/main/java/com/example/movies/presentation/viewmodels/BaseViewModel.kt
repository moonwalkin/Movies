package com.example.movies.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

open class BaseViewModel<T> : ViewModel() {
    protected val _items = MutableSharedFlow<T>()
    val items: SharedFlow<T>
        get() = _items
}