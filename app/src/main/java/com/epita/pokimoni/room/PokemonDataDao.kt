package com.epita.pokimoni.room

import android.arch.persistence.room.*

@Dao
interface PokemonDataDao {

    @Query("SELECT * FROM pokemonData WHERE `index` == :index")
    fun getByIndex(index: Int): PokemonData?

    @Query("SELECT * FROM pokemonData WHERE `name` == :name")
    fun getByName(name: String): PokemonData?

    @Query("SELECT * FROM pokemonData ORDER BY `index` ASC")
    fun getAll(): List<PokemonData>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemonData: PokemonData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(pokemonData: PokemonData)

    @Delete
    fun delete(pokemonData: PokemonData)

    @Query("DELETE FROM pokemonData")
    fun deleteAll()
}