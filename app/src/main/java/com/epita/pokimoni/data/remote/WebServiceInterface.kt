package com.epita.pokimoni.data.remote

import com.epita.pokimoni.Pokemon
import com.epita.pokimoni.PokemonItem
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
    fun getPokemonById(@Path("id") id: Int): Call<Pokemon>

    /**
     * Get a Pokemon with its name.
     */
    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name: String): Call<PokemonItem>
}