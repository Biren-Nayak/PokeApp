package com.example.pokeapp.models

data class PokeList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokeResult>
)