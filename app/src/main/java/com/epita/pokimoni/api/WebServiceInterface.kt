package com.epita.pokimoni.api

import com.epita.pokimoni.api.model.PokemonJsonModel
import retrofit2.Call
import retrofit2.http.*

/**
 * Web service interface to handle communication with PokéAPI.
 */
interface WebServiceInterface {

    /**
     * Get a Pokemon with its ID number.
     */
    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int): Call<PokemonJsonModel>

    /**
     * Get a Pokemon with its name.
     */
    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Call<PokemonJsonModel>

    /**
     * Get a Pokemon description with its ID number.
     */
    @GET("pokemon/{id}")
    fun getPokemonDescription(@Path("id") id: Int): Call<PokemonJsonModel>
}