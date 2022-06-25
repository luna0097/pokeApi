package com.example.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokeapiResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val result: List<Pokemon>
)
