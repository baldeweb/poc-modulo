package com.example.details.domain

import android.content.Context
import com.example.daycoval_service.domain.repository.BaseRepository
import com.example.shared_common.data.DetailPokemonAPI
import com.example.shared_common.data.model.DetailPokemonDTO
import com.example.shared_common.domain.DetailPokemonRepository

class DetailPokemonRepositoryImpl(
    context: Context
) : BaseRepository<DetailPokemonAPI>(context), DetailPokemonRepository {
    override suspend fun getPokemonDetail(endpoint: String): DetailPokemonDTO? =
        create<DetailPokemonAPI>().getPokemonDetail(endpoint).body()
}