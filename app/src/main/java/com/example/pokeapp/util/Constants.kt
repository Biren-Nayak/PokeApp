package com.example.pokeapp.util

import com.example.pokeapp.R

object Constants {
    const val BASE_URL = "https://pokeapi.co/api/v2/"
    const val POKEMON = "pokemon"
    const val FETCH_STATUS = "fetch_status"
    const val POKEMON_TYPE_IMAGE = "pokemon_type_image"
    const val POKEMON_TYPE_IMAGE_2 = "pokemon_type_image_second"
    const val POKEMON_TYPE_COLOR = "pokemon_type_color"
    const val POKEMON_TYPE_TINT = "pokemon_type_tint"
    const val WEIGHT = "weight"
    const val HEIGHT = "height"
    const val IMG_URL = "img_url"
    const val SET_STAT="set_stat"
    const val SET_EXP="set_exp"


    const val MAX_EXP = 1000
    const val MAX_STAT = 300
    const val OFFSET = "offset"
    const val LIMIT = "limit"


    fun Int.toKilograms(): Double = this.toDouble() / 10
    fun Int.toMeters(): Double = this.toDouble() / 10


     val pokemonTypesList = listOf(
         "bug",
         "dark",
         "dragon",
         "electric",
         "fairy",
         "fighting",
         "fire",
         "flying",
         "ghost",
         "grass",
         "ground",
         "ice",
         "normal",
         "poison",
         "psychic",
         "rock",
         "shadow",
         "steel",
         "unknown",
         "water"
     )

    val pokemonTypeImagesList = listOf(
        R.drawable.ic_bug,
        R.drawable.ic_dark,
        R.drawable.ic_dragon,
        R.drawable.ic_electric,
        R.drawable.ic_fairy,
        R.drawable.ic_fighting,
        R.drawable.ic_fire,
        R.drawable.ic_flying,
        R.drawable.ic_ghost,
        R.drawable.ic_grass,
        R.drawable.ic_ground,
        R.drawable.ic_ice,
        R.drawable.ic_normal,
        R.drawable.ic_poison,
        R.drawable.ic_psychic,
        R.drawable.ic_rock,
        R.drawable.ic_dark,
        R.drawable.ic_steel,
        R.drawable.ic_normal,
        R.drawable.ic_water
    )

    val pokemonTypeColorsList = arrayOf(
        R.color.bug,
        R.color.dark,
        R.color.dragon,
        R.color.electric,
        R.color.fairy,
        R.color.fighting,
        R.color.fire,
        R.color.flying,
        R.color.ghost,
        R.color.grass,
        R.color.ground,
        R.color.ice,
        R.color.normal,
        R.color.poison,
        R.color.psychic,
        R.color.rock,
        R.color.dark,
        R.color.steel,
        R.color.normal,
        R.color.water
    )
}