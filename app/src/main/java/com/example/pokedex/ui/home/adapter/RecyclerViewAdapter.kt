package com.example.pokedex.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.model.Pokemon
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(val actionClick: (String) -> Unit) : RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {
    private var pokemonList = mutableListOf<Pokemon>()
    fun setData(list: List<Pokemon>){
        pokemonList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Holder(layoutInflater.inflate(R.layout.pokemon_card_item, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val pokemon = pokemonList[position]
        val pokemonId = pokemon.url.let {
            it.split("/")[6]
        }
        holder.itemView.findViewById<TextView>(R.id.tvPokemonName).text = pokemon.name
        holder.itemView.findViewById<CardView>(R.id.cvPokemonItem).setOnClickListener {
            actionClick(pokemon.name)
        }

        val pokeImg = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"
        Picasso.get().load(pokeImg).into(holder.itemView.findViewById<ImageView>(R.id.ivPokemon))
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    class Holder(view: View): RecyclerView.ViewHolder(view)
}