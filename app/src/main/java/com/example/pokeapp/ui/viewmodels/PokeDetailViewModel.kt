package com.example.pokeapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.models.pokemonresponses.Pokemon
import com.example.pokeapp.network.PokeAPIService
import kotlinx.coroutines.launch

class PokeDetailViewModel(val id: Int): ViewModel() {

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
    get() = _pokemon

    init {
        getPokemonDetail()
    }


    private fun getPokemonDetail() = viewModelScope.launch {
        _pokemon.value = PokeAPIService.pokeAPI.getPokemonDetail(id)
    }

}