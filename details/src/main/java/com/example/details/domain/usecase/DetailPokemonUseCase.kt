package com.example.details.domain.usecase

import com.example.daycoval_service.data.model.ServiceErrorModel
import com.example.details.data.model.DetailPokemonDTO

interface DetailPokemonUseCase {
    suspend fun getPokemonDetail(
        endpoint: String,
        onSuccess: (DetailPokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    )
}