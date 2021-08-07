package com.example.shared_common.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//@Dao
//interface BaseDAO<T> {
//    @Insert
//    suspend fun save(userEntity: T)
//
//    @Query("SELECT * FROM PokemonTable WHERE id = :id")
//    suspend fun getById(id: String): List<PokemonEntity>
//
//    @Query("SELECT * FROM PokemonTable")
//    suspend fun getAll(): List<PokemonEntity>
//
//    @Query("DELETE FROM PokemonTable WHERE id = :id")
//    suspend fun deleteUser(id: Int)
//}