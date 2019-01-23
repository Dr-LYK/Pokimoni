package com.epita.pokimoni.ui.pokemon

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController

import com.epita.pokimoni.R

class PokemonFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = PokemonFragment()
    }

    private lateinit var buttonCancel: ImageView
    private lateinit var viewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)
        buttonCancel = view.findViewById(R.id.fragment_pokemon_button_cancel)

        buttonCancel.setOnClickListener(this@PokemonFragment)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.fragment_pokemon_button_cancel -> {
                    view?.findNavController()?.navigate(com.epita.pokimoni.R.id.pokedexFragment)
                }
            }
        }    }

}
