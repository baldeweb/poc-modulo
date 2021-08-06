package com.example.daycoval_service

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DayFactory {
    companion object {
        const val TIMEOUT = 61L
    }

    private fun <T> create(
        serviceAPI: Class<T>,
        url: String?,
        context: Context
    ): T {
        val okHttpClient = getOkHttpClient(context, TIMEOUT)
        val retrofit = Retrofit.Builder()
            .baseUrl(url ?: "")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient.build())
            .build()

        return retrofit.create(serviceAPI)
    }

    private fun getOkHttpClient(
        context: Context,
        timeout: Long
    ): OkHttpClient.Builder {
        val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClient.connectTimeout(timeout, TimeUnit.SECONDS)
        okHttpClient.readTimeout(timeout, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(timeout, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE

        okHttpClient.addInterceptor(interceptor)

        if (BuildConfig.DEBUG)
            okHttpClient.addInterceptor(ChuckerInterceptor.Builder(context).build())

        return okHttpClient
    }
}