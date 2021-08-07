package com.example.details.domain.usecase

import com.example.daycoval_service.data.model.ServiceErrorModel
import com.example.details.data.model.DetailPokemonDTO
import com.example.details.domain.repository.DetailPokemonRepository

class DetailPokemonUseCaseImpl(
    private val repository: DetailPokemonRepository
) : DetailPokemonUseCase {
    override suspend fun getPokemonDetail(
        endpoint: String,
        onSuccess: (DetailPokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    ) {
        //  TODO: pegar da DAO o endpoint usando um filtro
        repository.getPokemonDetail(endpoint, onSuccess, onError)
    }
}