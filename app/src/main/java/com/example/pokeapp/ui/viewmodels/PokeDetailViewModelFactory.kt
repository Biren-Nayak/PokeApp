package com.example.pokeapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PokeDetailViewModelFactory(
    private val id: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokeDetailViewModel::class.java)){
            return PokeDetailViewModel(id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}