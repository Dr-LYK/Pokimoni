package com.epita.pokimoni.ui.pokedex

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import com.epita.pokimoni.model.PokemonItem
import com.epita.pokimoni.repository.PokemonRepository

class PokedexViewModel : ViewModel() {

    private val repository = PokemonRepository()
    private lateinit var pokemonList: LiveData<List<PokemonItem>>

    fun getPokedex(): LiveData<List<PokemonItem>>{
        if (!::pokemonList.isInitialized) {
            pokemonList = MutableLiveData()
            fetchPokedex()
        }
        return pokemonList
    }

    private fun fetchPokedex() {
        if (::pokemonList.isInitialized) {
            pokemonList = repository.getPokedex()
        }
    }
}
