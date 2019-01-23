package com.epita.pokimoni.ui.pokedex

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.epita.pokimoni.R
import com.epita.pokimoni.model.PokemonItem

class PokedexAdapter(context: Context, pokemons: List<PokemonItem>) : ArrayAdapter<PokemonItem>(context, 0, pokemons)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {

        var convertedView: View? = convertView
        if (convertedView == null)
        {
            convertedView = LayoutInflater.from(context).inflate(R.layout.fragment_pokedex_cell, parent, false)
        }

        var viewHolder: PokemonViewHolder? = convertedView!!.tag as PokemonViewHolder?
        if (viewHolder == null) {
            viewHolder = PokemonViewHolder()
            viewHolder.index = convertedView.findViewById(R.id.fragment_pokedex_cell_id)
            viewHolder.name = convertedView.findViewById(R.id.fragment_pokedex_cell_name)
            viewHolder.type1 = convertedView.findViewById(R.id.fragment_pokedex_cell_type1)
            viewHolder.type2 = convertedView.findViewById(R.id.fragment_pokedex_cell_type2)
            viewHolder.counter = convertedView.findViewById(R.id.fragment_pokedex_cell_counter)
            convertedView.tag = viewHolder
        }

        val pokemon: PokemonItem = getItem(position)!!

        viewHolder.index!!.text = pokemon.id.toString()
        viewHolder.name!!.text = pokemon.name
        viewHolder.type1!!.text = pokemon.type1.typeName
        viewHolder.type2!!.text = pokemon.type2?.typeName
        viewHolder.counter!!.text = pokemon.count.toString()

        /*when (viewHolder.type!!.text)
        {
            "Normal" -> viewHolder.rate!!.background = ContextCompat.getDrawable(context, R.drawable.border_green)
            "Attention" -> viewHolder.rate!!.background = ContextCompat.getDrawable(context, R.drawable.border_orange)
            "Danger" -> viewHolder.rate!!.background = ContextCompat.getDrawable(context, R.drawable.border_red)
        }*/

        return convertedView
    }

    private inner class PokemonViewHolder
    {
        var index: TextView? = null
        var name: TextView? = null
        var type1: TextView? = null
        var type2: TextView? = null
        var counter: TextView? = null
    }
}
