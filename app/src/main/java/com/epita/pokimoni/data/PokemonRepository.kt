package com.epita.pokimoni.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.epita.pokimoni.Pokemon
import com.epita.pokimoni.PokemonItem
import com.epita.pokimoni.data.remote.WebServiceInterface
import com.epita.pokimoni.utils.STRING_API_BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRepository: PokemonRepositoryInterface {

    override val baseURL: String = STRING_API_BASE_URL
    override val jsonConverter: GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())
    override val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(jsonConverter).build()
    override val service: WebServiceInterface = retrofit.create(WebServiceInterface::class.java)


    override fun getPokemonById(id: Int): LiveData<Pokemon> {
        val data: MutableLiveData<Pokemon> = MutableLiveData()
        val call: Call<Pokemon> = service.getPokemonById(id)

        call.enqueue(object: Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
            }
        })
        return data
    }

    override fun getPokemonByName(name: String): LiveData<PokemonItem> {
        val data: MutableLiveData<PokemonItem> = MutableLiveData()
        val call: Call<PokemonItem> = service.getPokemonByName(name)

        call.enqueue(object: Callback<PokemonItem> {

            override fun onResponse(call: Call<PokemonItem>, response: Response<PokemonItem>) {
            }

            override fun onFailure(call: Call<PokemonItem>, t: Throwable) {
            }
        })
        return data
    }
}