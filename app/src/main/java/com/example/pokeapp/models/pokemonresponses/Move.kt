package com.example.pokeapp.models.pokemonresponses

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)