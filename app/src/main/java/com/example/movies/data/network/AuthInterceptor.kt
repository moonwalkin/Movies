package com.example.movies.data.network

import com.example.movies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalUrl = request.url
        val url = originalUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        val requestBuilder = request.newBuilder().url(url).build()
        return chain.proceed(requestBuilder)
    }
}