package com.epita.pokimoni.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.os.HandlerThread
import com.epita.pokimoni.MainActivity
import com.epita.pokimoni.model.Pokemon
import com.epita.pokimoni.model.PokemonItem
import com.epita.pokimoni.api.WebServiceInterface
import com.epita.pokimoni.api.model.PokemonJsonModel
import com.epita.pokimoni.model.TypeColor
import com.epita.pokimoni.room.PokemonData
import com.epita.pokimoni.room.PokemonDataBase
import com.epita.pokimoni.util.GlobalApplication
import com.epita.pokimoni.util.STRING_API_BASE_URL
import com.epita.pokimoni.util.Utils
import com.epita.pokimoni.util.WorkerThread
import com.google.gson.GsonBuilder
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

@Singleton
class PokemonRepository @Inject constructor(): PokemonRepositoryInterface {

    override val baseURL: String = STRING_API_BASE_URL
    override val jsonConverter: GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())
    override val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(jsonConverter).build()
    override val service: WebServiceInterface = retrofit.create(WebServiceInterface::class.java)
    override val database: PokemonDataBase = Room.databaseBuilder(GlobalApplication.appContext!!, PokemonDataBase::class.java, "pokemon.db").build()


    override fun getPokemon(id: Int): LiveData<Pokemon> {
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

    override fun getPokemonItem(id: Int): LiveData<PokemonItem> {
        val data: MutableLiveData<PokemonItem> = MutableLiveData()
        val call: Call<PokemonJsonModel> = service.getPokemon(id)

        val callback = object: Callback<PokemonJsonModel> {

            override fun onResponse(call: Call<PokemonJsonModel>, response: Response<PokemonJsonModel>) {
                val pokemonJsonModel: PokemonJsonModel? = response.body()
                var type1: TypeColor? = null
                var type2: TypeColor? = null

                if (pokemonJsonModel != null) {
                    if (pokemonJsonModel.types != null) {
                        if (pokemonJsonModel.types.size == 1) {
                            type1 = Utils.stringToTypeColor(pokemonJsonModel.types[0].type!!.name!!)
                            type2 = null
                        }
                        else if (pokemonJsonModel.types.size == 2) {
                            type1 = Utils.stringToTypeColor(pokemonJsonModel.types[0].type!!.name!!)
                            type2 = Utils.stringToTypeColor(pokemonJsonModel.types[1].type!!.name!!)
                        }
                    }
                    val pokemonItem = PokemonItem(pokemonJsonModel.id!!,pokemonJsonModel.name!!, type1!!, type2, 1)
                    data.value = pokemonItem
                }
            }

            override fun onFailure(call: Call<PokemonJsonModel>, t: Throwable) {
            }
        }

        call.enqueue(callback)
        return data
    }

    override fun insertPokemonItem(pokemonItem: PokemonItem) {
        val storedPokemon: PokemonData? = database.pokemonDataDao().getByIndex(pokemonItem.id)
        val newPokemon = PokemonData(
            0,
            pokemonItem.id,
            pokemonItem.name.toLowerCase(),
            pokemonItem.type1.typeName,
            pokemonItem.type2?.typeName,
            pokemonItem.count)

        if (storedPokemon == null) {
            database.pokemonDataDao().insert(newPokemon)
        }
        else {
            storedPokemon.count += 1
            database.pokemonDataDao().update(storedPokemon)
        }
    }

    override fun getPokedex(): LiveData<List<PokemonItem>> {
        val liveData: MutableLiveData<List<PokemonItem>> = MutableLiveData()
        val pokemonList: MutableList<PokemonItem> = ArrayList()

        val task = Thread(Runnable {
            database.pokemonDataDao().getAll()?.forEach { pokemonData ->
                val pokemon = PokemonItem(
                    pokemonData.index,
                    pokemonData.name,
                    Utils.stringToTypeColor(pokemonData.type1)!!,
                    Utils.stringToTypeColor(pokemonData.type2),
                    pokemonData.count)
                pokemonList.add(pokemon)
            }
        })
        task.start()
        task.join()
        liveData.value = pokemonList

        return liveData
    }

    override fun deletePokedex() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}