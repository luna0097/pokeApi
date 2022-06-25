package com.example.pokedex.data.service.retrofit

import com.example.pokedex.data.model.PokeapiResponse
import com.example.pokedex.data.model.PokemonInfoApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IPokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(@Query("limit" ) limit: Int, @Query("offset") offset: Int): Response<PokeapiResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): Response<PokemonInfoApiResponse>
}