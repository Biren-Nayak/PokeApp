package com.example.pokeapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapp.adapters.PokemonListAdapter
import com.example.pokeapp.databinding.FragmentHomeBinding
import com.example.pokeapp.ui.viewmodels.HomeViewModel
import com.example.pokeapp.ui.viewmodels.LoadingStates.*

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val viewModel: HomeViewModel by lazy {
            ViewModelProvider(this)[HomeViewModel::class.java]
        }

        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        val pokemonListAdapter = PokemonListAdapter()

        binding.rvHome.apply {
            adapter = pokemonListAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.pokemonList.observe(viewLifecycleOwner) { result ->
            pokemonListAdapter.differ.submitList(result)
        }



        viewModel.loadingStates.observe(viewLifecycleOwner) { states ->
            when (states) {
                LOADING -> showProgressBar()
                SUCCESS -> hideProgressBar()
                else -> {
                    hideProgressBar()
                    Toast.makeText(context, "No Internet Connection", LENGTH_SHORT).show()
                }

            }
        }

        return binding.root
    }

    private fun hideProgressBar(){
        binding.loadingHome.visibility = GONE
    }

    private fun showProgressBar(){
        binding.loadingHome.visibility = VISIBLE
    }

}