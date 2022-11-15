package com.example.pokeapp.network

import com.example.pokeapp.models.PokeList
import com.example.pokeapp.models.TypeList
import com.example.pokeapp.models.pokemonresponses.Pokemon
import com.example.pokeapp.util.Constants.ID
import com.example.pokeapp.util.Constants.LIMIT
import com.example.pokeapp.util.Constants.OFFSET
import com.example.pokeapp.util.Constants.POKEMON
import com.example.pokeapp.util.Constants.limit
import com.example.pokeapp.util.Constants.offset
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeAPI {

    @GET("$POKEMON?$OFFSET=$offset&$LIMIT=$limit")
    suspend fun getPokemon(): PokeList

    @GET("$POKEMON/{$ID}")
    suspend fun getPokemonDetail( @Path(ID) id: Int): Pokemon

    @GET("$POKEMON/{$ID}")
    suspend fun getPokemonType( @Path(ID) id: Int): TypeList
}