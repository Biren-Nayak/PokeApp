package com.example.pokeapp.models.pokemonresponses

import com.google.gson.annotations.SerializedName


data class Other(
    val dream_world: DreamWorld,
    @SerializedName("official-artwork")
    val official_artwork: OfficialArtwork
)