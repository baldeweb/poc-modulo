package com.wallace.shared_service.repository

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.wallace.shared_service.BuildConfig
import com.wallace.shared_common.mock.MockInterceptor
import com.wallace.shared_common.presentation.constants.Constants
import com.wallace.shared_common.presentation.constants.Constants.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseRepository<T>(val context: Context, private val shouldStartMock: Boolean? = null) {
    private val HTTP_OK = 200
    private val HTTP_UNAUTHORIZED = 401
    private val HTTP_INTERNAL_SERVER_ERROR = 500

    protected suspend inline fun <reified T, E> caller(
        crossinline response: suspend (T) -> Response<E>
    ): Response<E> = response.invoke(this.create())

    protected inline fun <reified T> create(): T {
        val okHttpClient = getOkHttpClient(context, Constants.TIMEOUT)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .client(okHttpClient.build())
            .build()

        return retrofit.create(T::class.java)
    }

    fun getOkHttpClient(
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

        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(ChuckerInterceptor.Builder(context).build())
            okHttpClient.addInterceptor(MockInterceptor(context, shouldStartMock ?: false))
        }

        return okHttpClient
    }

}