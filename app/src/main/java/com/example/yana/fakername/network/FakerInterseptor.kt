package com.example.yana.fakername.network

import com.example.yana.fakername.prefs.SharedPreferenceFaker
import okhttp3.Interceptor
import okhttp3.Response

class FakerInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        if (SharedPreferenceFaker.token.isNotEmpty()){
            request.addHeader(
                "X-API-KEY",
                SharedPreferenceFaker.token
            )
            request.addHeader(
                "Authorization",
                "Bearer " + SharedPreferenceFaker.token
            )
        }
        request.addHeader(name = "Accept", value = "application/json")

        return chain.proceed(request.build())
    }
}