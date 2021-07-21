package com.example.pokeapp.models.pokemonresponses

import com.squareup.moshi.Json

data class GenerationVi(
    @Json(name = "omegaruby-alphasapphire")
    val omega_ruby_alpha_sapphire: OmegarubyAlphasapphire,
    @Json(name = "x-y")
    val x_y: XY
)