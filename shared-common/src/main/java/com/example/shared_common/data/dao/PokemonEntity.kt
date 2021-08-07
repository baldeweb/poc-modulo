package com.example.shared_common.data.dao


import androidx.room.*
import com.example.shared_common.data.model.*

@Entity(tableName = "PokemonTable")
open class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo
    var abilities: String,
    @ColumnInfo
    var baseExperience: Int,
    @ColumnInfo
    var forms: String,
    @ColumnInfo
    var gameIndices: String,
    @ColumnInfo
    var height: Int,
    @ColumnInfo
    var heldItems: String,
    @ColumnInfo
    var isDefault: Boolean,
    @ColumnInfo
    var locationAreaEncounters: String,
    @ColumnInfo
    var moves: String,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var order: Int,
    @ColumnInfo
    var pastTypes: String,
    @ColumnInfo
    var species: String,
    @ColumnInfo
    var sprites: String,
    @ColumnInfo
    var stats: String,
    @ColumnInfo
    var types: String,
    @ColumnInfo
    var weight: Int
)