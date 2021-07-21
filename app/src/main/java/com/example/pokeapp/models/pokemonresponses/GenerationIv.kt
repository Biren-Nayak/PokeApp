package com.example.pokeapp.models.pokemonresponses

import com.squareup.moshi.Json

data class GenerationIv(
    @Json(name = "diamond-pearl")
    val diamond_pearl: DiamondPearl,
    @Json(name = "heartgold-soulsilver")
    val heart_gold_soul_silver: HeartgoldSoulsilver,
    val platinum: Platinum
)