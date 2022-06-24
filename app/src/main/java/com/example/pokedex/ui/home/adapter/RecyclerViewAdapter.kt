package com.example.pokedex.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.domain.model.Pokemon

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {
    private val pokemonList : List<Pokemon> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Holder(layoutInflater.inflate(R.layout.pokemon_card_item, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val pokemon = pokemonList[position]
        holder.itemView.findViewById<TextView>(R.id.tvPokemonName).text = pokemon.name
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    class Holder(view: View): RecyclerView.ViewHolder(view)
}