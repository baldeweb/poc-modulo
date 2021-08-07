package com.example.details.domain.repository

import android.content.Context
import com.example.daycoval_service.domain.repository.BaseRepository
import com.example.shared_common.data.DetailPokemonAPI
import com.example.details.data.model.DetailPokemonDTO
import com.example.shared_common.data.model.ServiceErrorModel

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