package com.wallace.details.domain

import android.content.Context
import com.wallace.shared_service.repository.BaseRepository
import com.wallace.details.data.DetailPokemonAPI
import com.wallace.details_public.domain.DetailPokemonRepository

class DetailPokemonRepositoryImpl(
    context: Context
) : BaseRepository<DetailPokemonAPI>(context), DetailPokemonRepository {
    override suspend fun getPokemonDetail(endpoint: String) = create<DetailPokemonAPI>().getPokemonDetail(endpoint)
}