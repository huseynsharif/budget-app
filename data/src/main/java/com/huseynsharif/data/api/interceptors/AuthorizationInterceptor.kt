package com.huseynsharif.data.api.interceptors

import com.huseynsharif.data.api.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader(API_KEY, SessionManager.apiKey)
        request.addHeader(API_HOST, SessionManager.apiHost)
        return chain.proceed(request.build())
    }

    companion object {
        private const val API_KEY = "X-RapidAPI-Key"
        private const val API_HOST = "X-RapidAPI-Host"
    }
}