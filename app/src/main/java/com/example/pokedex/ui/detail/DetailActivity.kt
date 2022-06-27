package com.example.pokedex.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.databinding.ActivityDetailBinding
import com.example.pokedex.ui.detail.viewModel.DetailActivityViewModel
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(DetailActivityViewModel::class.java)
        getIntentExtras()
    }

    private fun getIntentExtras(){
        val pokemonName = intent.extras?.get("name") as String
        getPokemonData(pokemonName)
    }

    @SuppressLint("SetTextI18n")
    private fun getPokemonData(name : String){
        viewModel.getPokemonInfo(name)

        viewModel.pokemonInfo.observe(this){ pokeInfo ->
            binding.tvName.text = pokeInfo.name
            binding.tvPokemonId.text = "#${pokeInfo.id}"
            binding.tvAltura.text = "Altura - ${pokeInfo.height}"
            binding.tvPeso.text = "Peso - ${pokeInfo.weight}"
            binding.tvType.text = "Tipo - ${pokeInfo.types.map { it.type.name }}"
            Picasso.get().load(pokeInfo.sprites.front_default).into(binding.ivFrontDefault)
            Picasso.get().load(pokeInfo.sprites.back_default).into(binding.ivBackDefault)
        }

        viewModel.isLoading.observe(this){
            binding.pbPokeInfo.isVisible = it
        }
    }

}