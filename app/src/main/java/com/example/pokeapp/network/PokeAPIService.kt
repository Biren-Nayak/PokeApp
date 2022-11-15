package com.example.pokeapp.network

import com.example.pokeapp.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokeAPIService{

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val pokeAPI: PokeAPI by lazy{ retrofit.create(PokeAPI::class.java) }
}






