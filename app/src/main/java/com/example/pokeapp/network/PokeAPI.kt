package com.example.pokeapp.network

import com.example.pokeapp.models.PokeList
import com.example.pokeapp.models.pokemonresponses.Pokemon
import com.example.pokeapp.util.Constants.LIMIT
import com.example.pokeapp.util.Constants.OFFSET
import com.example.pokeapp.util.Constants.POKEMON
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeAPI {

    @GET(POKEMON)
    suspend fun getPokemonList(@Query(OFFSET) offset: Int, @Query(LIMIT) limit: Int): PokeList


    @GET
    suspend fun getPokemonDetail(@Url url: String): Pokemon

}