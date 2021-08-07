package com.example.details.domain.usecase

import com.example.details.data.model.DetailPokemonDTO
import com.example.shared_common.data.model.ServiceErrorModel

interface DetailPokemonUseCase {
    suspend fun getPokemonDetail(
        endpoint: String,
        onSuccess: (DetailPokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    )
}