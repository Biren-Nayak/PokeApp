package com.example.pokeapp.repository

import com.example.pokeapp.models.PokemonEntry
import com.example.pokeapp.network.PokeAPI
import com.example.pokeapp.network.PokeAPIService
import com.example.pokeapp.util.Constants

class PokeRepository{

    private val api: PokeAPI = PokeAPIService.pokeAPI

    suspend fun getPokemonFromId(id: Int) = api.getPokemonDetail(id)


    suspend fun fetchPokemonList(): List<PokemonEntry> {

            val pokemonResults = api.getPokemon().results

            val pokemonEntry = pokemonResults.mapIndexed { _, pokeResult ->
                val id = pokeResult.url.dropLast(1).takeLastWhile { it.isDigit() }
                val url = "${Constants.ALT_URL}$id.${Constants.PNG}"
                val type = getPokemonTypeFromId(id.toInt()).types[0].type.name
                PokemonEntry(id.toInt(), pokeResult.name, url, type)
            }
        return pokemonEntry
    }

    private suspend fun getPokemonTypeFromId(id: Int) = api.getPokemonType(id)
}