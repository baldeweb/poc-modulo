package com.example.details.domain.repository

import com.example.details.data.model.DetailPokemonDTO
import com.example.shared_common.data.model.ServiceErrorModel

interface DetailPokemonRepository {
    suspend fun getPokemonDetail(
        endpoint: String,
        onSuccess: (DetailPokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    )
}