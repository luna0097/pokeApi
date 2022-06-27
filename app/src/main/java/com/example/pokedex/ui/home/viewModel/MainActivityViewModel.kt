package com.example.pokedex.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.service.retrofit.IPokemonApi
import com.example.pokedex.data.service.retrofit.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    private val retrofit = RetrofitHelper.getRetrofit()
    private val pokeApi: IPokemonApi = retrofit.create(IPokemonApi::class.java)

    val pokemonList = MutableLiveData<List<Pokemon>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getPokemons(offset: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            val call = pokeApi.getPokemons(20, offset)
            if (call.isSuccessful){
                call.body()?.result.let {
                    pokemonList.postValue(it)
                    isLoading.postValue(false)
                }
            }
        }
    }

}