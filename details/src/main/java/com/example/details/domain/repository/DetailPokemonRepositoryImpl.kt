package com.example.details.domain.repository

import com.example.daycoval_service.data.model.ServiceErrorModel
import com.example.daycoval_service.domain.repository.BaseRepository
import com.example.daycoval_service.data.DetailPokemonAPI
import com.example.details.data.model.DetailPokemonDTO

class DetailPokemonRepositoryImpl(
    private val api: DetailPokemonAPI
) : BaseRepository<DetailPokemonAPI>(api), DetailPokemonRepository {
    override suspend fun getPokemonDetail(
        endpoint: String,
        onSuccess: (DetailPokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    ) {
        serviceCaller(api.getPokemonDetail(endpoint).await(), {
            onSuccess.invoke(it as DetailPokemonDTO)
        }, {
            onError.invoke(it)
        })
    }
}