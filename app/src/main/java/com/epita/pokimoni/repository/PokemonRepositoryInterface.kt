package com.epita.pokimoni.repository

import android.arch.lifecycle.LiveData
import com.epita.pokimoni.model.Pokemon
import com.epita.pokimoni.model.PokemonItem
import com.epita.pokimoni.api.WebServiceInterface
import com.epita.pokimoni.room.PokemonDataBase
import com.epita.pokimoni.util.WorkerThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface PokemonRepositoryInterface {

    val baseURL: String
    val jsonConverter: GsonConverterFactory
    val retrofit: Retrofit
    val service: WebServiceInterface
    val database: PokemonDataBase

    fun getPokemon(id: Int): LiveData<Pokemon>
    fun getPokemonItem(id: Int): LiveData<PokemonItem>
    fun insertPokemonItem(pokemonItem: PokemonItem)
    fun getPokedex(): LiveData<List<PokemonItem>>
    fun deletePokedex()
}