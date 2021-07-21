package com.example.pokeapp.models.pokemonresponses

import com.squareup.moshi.Json

data class Other(
    val dream_world: DreamWorld,
    @Json(name = "official-artwork")
    val official_artwork: OfficialArtwork
)