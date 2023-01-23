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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokeapp.R
import com.example.pokeapp.adapters.PokemonListAdapter
import com.example.pokeapp.databinding.FragmentHomeBinding
import com.example.pokeapp.viewmodels.HomeViewModel

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by activityViewModels()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentHomeBinding.inflate(inflater)
        resetColorTint()
        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }



    private fun resetColorTint() {
        val color = ContextCompat.getColor(requireContext(), R.color.black)
        requireActivity().window?.statusBarColor = color
        (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(color.toDrawable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PokemonListAdapter(
            PokemonListAdapter.PokemonListener { pokemon ->
                viewModel.onPokemonSelected(pokemon)
                findNavController().navigate(R.id.action_homeFragment_to_pokeDetailFragment)

            }
        ).apply {
            binding.rvHome.adapter = this
            binding.rvHome.layoutManager = GridLayoutManager(context, 2)
            viewModel.pokemonList.observe(viewLifecycleOwner) {
                submitList(it)
            }

        }
    }
}