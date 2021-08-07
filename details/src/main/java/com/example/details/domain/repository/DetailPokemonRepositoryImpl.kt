package com.example.details.domain.repository

import android.content.Context
import com.example.daycoval_service.data.DetailPokemonAPI
import com.example.daycoval_service.data.model.ServiceErrorModel
import com.example.daycoval_service.domain.repository.BaseRepository
import com.example.details.data.model.DetailPokemonDTO

class DetailPokemonRepositoryImpl(
    context: Context
) : BaseRepository<DetailPokemonAPI>(context), DetailPokemonRepository {
    override suspend fun getPokemonDetail(
        endpoint: String,
        onSuccess: (DetailPokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    ) {
        serviceCaller(create<DetailPokemonAPI>().getPokemonDetail(endpoint).await(), {
            onSuccess.invoke(it as DetailPokemonDTO)
        }, {
            onError.invoke(it)
        })
    }
}