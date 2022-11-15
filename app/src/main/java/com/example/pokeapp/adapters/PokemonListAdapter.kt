package com.example.pokeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapp.databinding.ItemPokemonBinding
import com.example.pokeapp.models.PokemonEntry
import com.example.pokeapp.ui.fragments.HomeFragmentDirections

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {

    inner class PokemonListViewHolder(val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {

        return PokemonListViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent,
                false
            )
        )
    }

    private val differCallback = object : DiffUtil.ItemCallback<PokemonEntry>(){
        override fun areItemsTheSame(oldItem: PokemonEntry, newItem: PokemonEntry) =  oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PokemonEntry, newItem: PokemonEntry) = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: PokemonListAdapter.PokemonListViewHolder, position: Int) {
        val view = holder.binding
        val pokemon = differ.currentList[position]
        view.pokeListItem.setOnClickListener {
            holder.binding.root.findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToPokeDetailFragment(pokemon.id))
        }
        view.tvPokemonName.text = pokemon.name
        view.type.text = pokemon.type

        Glide.with(view.root)
            .load(pokemon.imgUrl)
            .into(view.pokeImage)

    }

    override fun getItemCount() = differ.currentList.size


}