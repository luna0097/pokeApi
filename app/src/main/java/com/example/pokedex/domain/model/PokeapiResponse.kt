package com.example.pokedex.domain.model

data class PokeapiResponse(
    val count : Int,
    val next : String,
    val previous : String,
    val results: Pokemon,
)
