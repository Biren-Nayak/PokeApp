package com.example.pokeapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokeapp.databinding.FragmentPokeDetailBinding
import com.example.pokeapp.models.pokemonresponses.Pokemon
import com.example.pokeapp.ui.viewmodels.PokeDetailViewModel
import com.example.pokeapp.ui.viewmodels.PokeDetailViewModelFactory

class PokeDetailFragment: Fragment() {

    private lateinit var binding : FragmentPokeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokeDetailBinding.inflate(layoutInflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner


        val id = PokeDetailFragmentArgs.fromBundle(requireArguments()).pokeId

        val viewModelFactory = PokeDetailViewModelFactory(id)

        val viewModel = ViewModelProvider(this,viewModelFactory)[PokeDetailViewModel::class.java]

        viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
            updateUI(pokemon)
        }


        return binding.root
    }

    private fun updateUI(pokemon: Pokemon) {

        Glide.with(this)
            .load(pokemon.sprites.other.official_artwork.front_default)
            .into(binding.imgPokemon)

        binding.txtNameValue.text = pokemon.name
        binding.txtHeightValue.text = pokemon.height.toString()
        binding.txtWeightValue.text = pokemon.weight.toString()
        binding.txtTypeValue.text = pokemon.types[0].type.name
        binding.txtHpValue.text = pokemon.stats[0].base_stat.toString()
        binding.txtSpecialAttackValue.text = pokemon.stats[3].base_stat.toString()
        binding.txtSpecialDefenceValue.text = pokemon.stats[4].base_stat.toString()
        binding.txtSpeedValue.text = pokemon.stats[5].base_stat.toString()

    }
}