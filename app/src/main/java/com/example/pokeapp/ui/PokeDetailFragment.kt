package com.example.pokeapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokeapp.databinding.FragmentPokeDetailBinding
import com.example.pokeapp.util.Constants.pokemonTypeColorsList
import com.example.pokeapp.util.Constants.pokemonTypesList
import com.example.pokeapp.viewmodels.HomeViewModel

class PokeDetailFragment: Fragment() {

    private lateinit var binding : FragmentPokeDetailBinding
    private val viewModel: HomeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokeDetailBinding.inflate(layoutInflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        val type = viewModel.selectedPokemon.value?.types?.first()?.type?.name
        setColor(type)
        binding.viewModel = viewModel
        return binding.root
    }



    private fun setColor(type: String?) {
        val color = ContextCompat.getColor(requireContext(), pokemonTypeColorsList[pokemonTypesList.indexOf(type)])
        binding.materialCardView.setCardBackgroundColor(color)
        requireActivity().window?.statusBarColor = color
        requireActivity().actionBar?.setBackgroundDrawable(color.toDrawable())
        (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(color.toDrawable())

    }
}



