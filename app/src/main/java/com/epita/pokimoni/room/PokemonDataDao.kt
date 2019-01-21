package com.epita.pokimoni.room

import android.arch.persistence.room.*

@Dao
interface PokemonDataDao {

    @Query("SELECT * FROM pokemonData WHERE `index` == :index")
    fun getByIndex(index: Int): PokemonData

    @Query("SELECT * FROM pokemonData WHERE `name` == :name")
    fun getByName(name: String): PokemonData

    @Query("SELECT * FROM pokemonData")
    fun getAll(): List<PokemonData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemonData: PokemonData)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(pokemonData: PokemonData)
}