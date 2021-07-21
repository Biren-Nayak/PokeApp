package com.example.pokeapp.network

import com.example.pokeapp.models.PokeList
import com.example.pokeapp.models.pokemonresponses.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeAPI {

    @GET("api/v2/pokemon?offset=0&limit=1118")
    suspend fun getPokemon(): PokeList

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id")
        id: Int
    ): Pokemon
}