package com.example.daycoval_service.domain.repository

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.daycoval_service.BuildConfig
import com.example.shared_common.data.model.ServiceErrorModel
import com.example.shared_common.data.mock.MockInterceptor
import com.example.shared_common.presentation.constants.Constants
import com.example.shared_common.presentation.constants.Constants.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

open class BaseRepository<T>(val context: Context, private val shouldStartMock: Boolean? = null) {
    private val HTTP_OK = 200
    private val HTTP_UNAUTHORIZED = 401
    private val HTTP_INTERNAL_SERVER_ERROR = 500

    protected suspend inline fun <reified T, E> caller(
        crossinline response: suspend (T) -> Response<E>
    ): E? = response.invoke(this.create()).body()

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

    protected fun <T> serviceCaller(
        api: Response<T>,
        onSuccess: (T) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    ) {
        if (api.code() == HTTP_OK) {
            try {
                if (api.body() == null) {
                    errorResponse(api, onError)
                    return
                }
                api.body()?.let { onSuccess.invoke(it) }
            } catch (exception: IOException) {
                errorResponse(api, onError)
            }
        } else {
            errorResponse(api, onError)
        }
    }

    private fun errorResponse(
        api: Response<*>,
        onError: (ServiceErrorModel) -> Unit
    ) {
        try {
            applyThrowableResponse(api, onError)
        } catch (exception: IOException) {
            onError.invoke(ServiceErrorModel(api.code(), Throwable(api.errorBody().toString())))
        }
    }

    @Synchronized
    private fun applyThrowableResponse(
        api: Response<*>,
        onError: (ServiceErrorModel) -> Unit
    ) {
        val error = ServiceErrorModel()
        error.httpCode = api.code()

        val errorSerialized = (api.errorBody()?.bytes() as ByteArray).toString()
        error.throwable = Throwable(errorSerialized)

        when (error.httpCode) {
            HTTP_UNAUTHORIZED -> onError.invoke(error)
            in HTTP_INTERNAL_SERVER_ERROR..599 -> onError.invoke(error)
            else -> onError.invoke(ServiceErrorModel(500))
        }
    }
}