package com.example.movies.domain

sealed class State<T> {
    class Loading<T> : State<T>()
    class Success<T>(val data: T) : State<T>()
    class Error<T>(val message: String) : State<T>()
}

