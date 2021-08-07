package com.example.shared_common.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDAO {
    @Insert
    suspend fun save(userEntity: PokemonEntity)

    @Query("SELECT * FROM PokemonTable WHERE id = :id")
    suspend fun getById(id: String): List<PokemonEntity>

    @Query("SELECT * FROM PokemonTable")
    suspend fun getAll(): List<PokemonEntity>

    @Query("DELETE FROM PokemonTable WHERE id = :id")
    suspend fun delete(id: Int)
}