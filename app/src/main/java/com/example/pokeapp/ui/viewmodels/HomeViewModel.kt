package com.example.pokeapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.models.PokeResult
import com.example.pokeapp.models.PokemonEntry
import com.example.pokeapp.models.pokemonresponses.Pokemon
import com.example.pokeapp.network.PokeAPIService
import com.example.pokeapp.ui.viewmodels.LoadingStates.*
import kotlinx.coroutines.launch
import java.lang.Exception

enum class  LoadingStates{LOADING, SUCCESS, ERROR }

class HomeViewModel: ViewModel() {

    private val _loadingStates = MutableLiveData<LoadingStates>()
    val loadingStates : LiveData<LoadingStates>
    get() = _loadingStates

    private val _pokemonList = MutableLiveData<List<PokemonEntry>>()
    val pokemonList: LiveData<List<PokemonEntry>>
    get() = _pokemonList



    private fun getPokemon () = viewModelScope.launch {
        _loadingStates.value = LOADING
        try{
            val pokemonResults = PokeAPIService.pokeAPI.getPokemon().results
            val pokemonEntry = pokemonResults.mapIndexed { index, pokeResult ->
                val id = pokeResult.url.dropLast(1).takeLastWhile { it.isDigit() }
                val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
                PokemonEntry(id.toInt(), pokeResult.name, url)
            }
            _pokemonList.value = pokemonEntry
            _loadingStates.value = SUCCESS
        }catch (e: Exception){
            _loadingStates.value = ERROR
            Log.d("HomeViewModel", e.message.toString())
        }

    }

    init {
        getPokemon()
    }


}