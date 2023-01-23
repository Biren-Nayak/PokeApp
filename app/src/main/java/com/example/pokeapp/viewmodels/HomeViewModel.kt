package com.example.pokeapp.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.pokeapp.models.pokemonresponses.Pokemon
import com.example.pokeapp.pagination.PokePagingSource
import com.example.pokeapp.repository.PokeRepository
import com.example.pokeapp.util.Constants.ITEMS_PER_PAGE
import com.example.pokeapp.viewmodels.HomeViewModel.LoadingStates.ERROR
import com.example.pokeapp.viewmodels.HomeViewModel.LoadingStates.SUCCESS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PokeRepository,
    private val pagingSource: PokePagingSource,
): ViewModel() {
    


    enum class  LoadingStates{LOADING, SUCCESS, ERROR }

    private val _fetchStatus = MutableLiveData<LoadingStates>()
    val fetchStates : LiveData<LoadingStates> = _fetchStatus

    private val _pokemonList = MutableLiveData(listOf<Pokemon>())
//    val pokemonList: LiveData<List<Pokemon>> = _pokemonList


    val pokemonList: LiveData<PagingData<Pokemon>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { pagingSource }
    )
        .liveData
        .cachedIn(viewModelScope)

    private val _selectedPokemon = MutableLiveData<Pokemon>()
    val selectedPokemon: LiveData<Pokemon> = _selectedPokemon



    private fun getPokemon() =  viewModelScope.launch{

        _fetchStatus.value = LoadingStates.LOADING

        try{
            repository.getPokemonList(0, 20).results.forEach{ pokeResult ->
                _pokemonList.value = _pokemonList.value?.plus(listOf(repository.getPokemonDetail(pokeResult.url)))
            }

            _fetchStatus.value = SUCCESS
        }catch (e: Exception){
            _fetchStatus.value = ERROR
            Log.e(TAG, e.message.toString())
        }

    }

    fun onPokemonSelected(pokemon: Pokemon) { _selectedPokemon.value = pokemon }

    init { getPokemon() }

}
