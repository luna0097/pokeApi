package com.example.pokedex.data.model

data class PokemonInfoApiResponse(
    val id : Int,
    val name: String,
    val height : Int,
    val sprites : Sprites,
    val types : List<Types>,
    val weight : Int,
)

data class Sprites(
    val back_default: String,
    val front_default: String,
)
data class Types(
    val slot: Int,
    val type: Type,
)

data class Type(
    val name: String,
    val url: String,
)