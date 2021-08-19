package com.example.details.domain

import android.content.Context
import com.example.daycoval_service.domain.repository.BaseRepository
import com.example.details.data.DetailPokemonAPI
import com.example.details_public.data.model.DetailPokemonDTO
import com.example.details_public.domain.DetailPokemonRepository

class DetailPokemonRepositoryImpl(
    context: Context
) : BaseRepository<DetailPokemonAPI>(context), DetailPokemonRepository {
    //override suspend fun getPokemonDetail(endpoint: String) = caller(DetailPokemonAPI::getPokemonDetail)
    override suspend fun getPokemonDetail(endpoint: String): DetailPokemonDTO? =
        create<DetailPokemonAPI>().getPokemonDetail(endpoint).body()
}