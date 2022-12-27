package com.example.pokeapp.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.models.PokemonEntry
import com.example.pokeapp.models.pokemonresponses.Pokemon
import com.example.pokeapp.repository.PokeRepository
import com.example.pokeapp.viewmodels.HomeViewModel.LoadingStates.ERROR
import com.example.pokeapp.viewmodels.HomeViewModel.LoadingStates.SUCCESS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PokeRepository): ViewModel() {

    enum class  LoadingStates{LOADING, SUCCESS, ERROR }


    private val _fetchStatus = MutableLiveData<LoadingStates>()
    val fetchStates : LiveData<LoadingStates>
    get() = _fetchStatus

    private val _pokemonList = MutableLiveData<List<PokemonEntry>>()
    val pokemonList: LiveData<List<PokemonEntry>>
    get() = _pokemonList

    private val _selectedPokemon = MutableLiveData<Pokemon>()
    val selectedPokemon: LiveData<Pokemon>
    get() = _selectedPokemon


    private fun getPokemon() = viewModelScope.launch {
        _fetchStatus.value = LoadingStates.LOADING
        try{
            _pokemonList.value = repository.fetchPokemonList()
            _fetchStatus.value = SUCCESS
        }catch (e: Exception){
            _fetchStatus.value = ERROR
            Log.d(TAG, e.message.toString())
        }

    }

    fun onPokemonSelected(pokemon: PokemonEntry) = viewModelScope.launch{
        _selectedPokemon.value = repository.getPokemonFromId(pokemon.id)
    }



    init { getPokemon() }


}
