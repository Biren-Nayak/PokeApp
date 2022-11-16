package com.example.pokeapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokeapp.databinding.FragmentPokeDetailBinding
import com.example.pokeapp.ui.viewmodels.HomeViewModel

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

        binding.viewModel = viewModel

        return binding.root
    }

}