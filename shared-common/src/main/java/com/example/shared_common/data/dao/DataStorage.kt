package com.example.shared_common.data.dao

class DataStorage(private val dao: PokemonDAO) {
    suspend fun save(entity: PokemonEntity) {
        dao.save(entity)
    }

    suspend fun getPokemonByName(id: String): List<PokemonEntity> {
        return dao.getById(id) as ArrayList<PokemonEntity>
    }

    suspend fun getAll(): List<PokemonEntity> {
        return dao.getAll() as ArrayList<PokemonEntity>
    }

    suspend fun delete(id: Int) {
        dao.delete(id)
    }
}