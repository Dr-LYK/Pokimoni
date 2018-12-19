package com.epita.pokimoni.data

import android.arch.lifecycle.LiveData
import com.epita.pokimoni.Pokemon
import com.epita.pokimoni.PokemonItem
import com.epita.pokimoni.data.remote.WebServiceInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface PokemonRepositoryInterface {

    val baseURL: String
    val jsonConverter: GsonConverterFactory
    val retrofit: Retrofit
    val service: WebServiceInterface

    fun getPokemonById(id: Int): LiveData<Pokemon>
    fun getPokemonByName(name: String): LiveData<PokemonItem>
}