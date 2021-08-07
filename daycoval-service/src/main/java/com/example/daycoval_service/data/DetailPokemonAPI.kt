package com.example.daycoval_service.data

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface DetailPokemonAPI {

    @Headers("Content-Type: application/json")
    @GET
    fun getPokemonDetail(@Url endpoint: String): Deferred<Response<ResponseBody>>
}