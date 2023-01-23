package com.example.pokeapp.repository

import com.example.pokeapp.models.PokeResult
import com.example.pokeapp.network.PokeAPI
import javax.inject.Inject

class PokeRepository @Inject constructor(private val api: PokeAPI){

    suspend fun getPokemonDetail(url: String) = api.getPokemonDetail(url)

    suspend fun getPokemonList(offset: Int, limit: Int) = api.getPokemonList(offset, limit)

    suspend fun toPokemonList(pokeResults: List<PokeResult>) = pokeResults.map { pokeResult ->
        getPokemonDetail(pokeResult.url)
    }

}