package com.epita.pokimoni.ui.pokedex

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import androidx.navigation.findNavController

import com.epita.pokimoni.R
import com.epita.pokimoni.model.PokemonItem

class PokedexFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = PokedexFragment()
    }

    private lateinit var viewModel: PokedexViewModel
    private lateinit var listView: ListView
    private lateinit var buttonBack: ImageView


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PokedexViewModel::class.java)

        listView = view.findViewById(R.id.fragment_pokedex_list_view)
        buttonBack = view.findViewById(R.id.fragment_pokedex_button_back)

        viewModel.getPokedex().observe(this, Observer<List<PokemonItem>> { pokemonList ->
            val adapter = PokedexAdapter(context!!, pokemonList!!)
            listView.adapter = adapter
        })

        buttonBack.setOnClickListener(this@PokedexFragment)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.fragment_pokedex_button_back -> {
                    view?.findNavController()?.navigate(com.epita.pokimoni.R.id.homeFragment)
                }
            }
        }    }
}
