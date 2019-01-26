package com.epita.pokimoni.ui.capture

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import com.epita.pokimoni.model.PokemonItem
import com.epita.pokimoni.repository.PokemonRepository
import com.epita.pokimoni.util.Utils
import javax.inject.Inject

class CaptureViewModel() : ViewModel() {

    private val repository = PokemonRepository()
    private lateinit var information: MutableLiveData<String>
    private lateinit var file: MutableLiveData<String>
    private lateinit var pokemon: LiveData<PokemonItem>

    fun getInformation(): LiveData<String> {
        if (!::information.isInitialized) {
            information = MutableLiveData()
        }
        return information
    }

    fun setInformation(str: String?) {
        if (::information.isInitialized) {
            information.value = str
        }
    }

    fun getFile(): LiveData<String> {
        if (!::file.isInitialized) {
            file = MutableLiveData()
            file.value = Utils.getRandomPokemon()
        }
        return file
    }

    fun getPokemon(): LiveData<PokemonItem> {
        if (!::pokemon.isInitialized) {
            pokemon = MutableLiveData()
            fetchPokemon()
        }
        return pokemon
    }

    private fun fetchPokemon() {
        if (::file.isInitialized) {
            pokemon = repository.getPokemonItem(Utils.fileToIndex(file.value!!))
        }
    }

    fun savePokemon() {
        if (::pokemon.isInitialized && pokemon.value != null) {
            repository.insertPokemonItem(pokemon.value!!)
        }
    }
}
