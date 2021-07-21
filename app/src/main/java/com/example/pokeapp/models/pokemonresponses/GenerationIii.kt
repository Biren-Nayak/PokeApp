package com.example.pokeapp.models.pokemonresponses

import com.squareup.moshi.Json

data class GenerationIii(
    val emerald: Emerald,
    @Json(name = "firered-leafgreen")
    val fire_red_leaf_green: FireredLeafgreen,

    @Json(name = "ruby-sapphire")
    val ruby_sapphire: RubySapphire
)