package com.example.pokeapp.di

import com.example.pokeapp.network.PokeAPI
import com.example.pokeapp.repository.PokeRepository
import com.example.pokeapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun myRepoImpl(api: PokeAPI) = PokeRepository(api)

    @Provides
    @Singleton
    fun myApiImpl(): PokeAPI = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()
        .create(PokeAPI::class.java)
}