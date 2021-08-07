package com.example.details.domain.usecase

import com.example.details.data.model.DetailPokemonDTO
import com.example.details.domain.repository.DetailPokemonRepository
import com.example.shared_common.data.model.ServiceErrorModel

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