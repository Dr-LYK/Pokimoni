package com.epita.pokimoni.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.epita.pokimoni.model.Pokemon
import com.epita.pokimoni.model.PokemonItem
import com.epita.pokimoni.api.WebServiceInterface
import com.epita.pokimoni.api.model.PokemonJsonModel
import com.epita.pokimoni.util.STRING_API_BASE_URL
import com.google.gson.GsonBuilder
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Singleton
class PokemonRepository @Inject constructor(): PokemonRepositoryInterface {

    override val baseURL: String = STRING_API_BASE_URL
    override val jsonConverter: GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())
    override val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(jsonConverter).build()
    override val service: WebServiceInterface = retrofit.create(WebServiceInterface::class.java)

    override fun getPokemonById(id: Int): LiveData<Pokemon> {
        val data: MutableLiveData<Pokemon> = MutableLiveData()
        val call: Call<PokemonJsonModel> = service.getPokemon(id)

        call.enqueue(object: Callback<PokemonJsonModel> {
            override fun onResponse(call: Call<PokemonJsonModel>, response: Response<PokemonJsonModel>) {
            }

            override fun onFailure(call: Call<PokemonJsonModel>, t: Throwable) {
            }
        })
        return data
    }

    override fun getPokemonByName(name: String): LiveData<PokemonItem> {
        val data: MutableLiveData<PokemonItem> = MutableLiveData()
        val call: Call<PokemonJsonModel> = service.getPokemon(name)

        call.enqueue(object: Callback<PokemonJsonModel> {

            override fun onResponse(call: Call<PokemonJsonModel>, response: Response<PokemonJsonModel>) {
            }

            override fun onFailure(call: Call<PokemonJsonModel>, t: Throwable) {
            }
        })
        return data
    }
}