package com.example.pokedex.ui.detail.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.data.model.PokemonInfoApiResponse
import com.example.pokedex.data.service.retrofit.IPokemonApi
import com.example.pokedex.data.service.retrofit.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivityViewModel : ViewModel() {
    private val retrofit = RetrofitHelper.getRetrofit()
    private val pokeApi: IPokemonApi = retrofit.create(IPokemonApi::class.java)

    val pokemonInfo = MutableLiveData<PokemonInfoApiResponse>()
    val isLoading = MutableLiveData<Boolean>()

    fun getPokemonInfo(name: String){
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            val call = pokeApi.getPokemonDetail(name)
            if (call.isSuccessful){
                call.body()?.let {
                    pokemonInfo.postValue(it)
                    isLoading.postValue(false)
                }
                Log.d("TAG", "getPokemonInfo: ${call.body()}")
            }
        }
    }
}