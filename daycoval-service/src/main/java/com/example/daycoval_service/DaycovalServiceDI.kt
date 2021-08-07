package com.example.daycoval_service

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.daycoval_service.data.DetailPokemonAPI
import com.example.daycoval_service.data.PokemonAPI
import com.example.daycoval_service.domain.repository.BaseRepository
import com.example.daycoval_service.presentation.constants.Constants.BASE_URL
import com.example.daycoval_service.presentation.constants.Constants.TIMEOUT
import com.example.daycoval_service.presentation.BaseViewModel
import com.example.daycoval_service.presentation.adapter.CoroutineCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DaycovalServiceDI {
    fun getModule(): Module {
        return module {
            viewModel { BaseViewModel() }
            single { BaseRepository<Class<*>>(get()) }
            single { create<PokemonAPI>(get()) }
            single { create<DetailPokemonAPI>(get()) }
        }
    }
}

inline fun <reified T> create(
    context: Context
): T {
    val okHttpClient = getOkHttpClient(context, TIMEOUT)
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
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

    if (BuildConfig.DEBUG)
        okHttpClient.addInterceptor(ChuckerInterceptor.Builder(context).build())

    return okHttpClient
}