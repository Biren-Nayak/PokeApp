package com.example.pokeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.adapters.PokemonListAdapter.PokemonListViewHolder
import com.example.pokeapp.databinding.ItemPokemonBinding
import com.example.pokeapp.models.pokemonresponses.Pokemon


class PokemonListAdapter(private val clickListener: PokemonListener) :PagingDataAdapter<Pokemon, PokemonListViewHolder>(DiffCallBack) {

    class PokemonListViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: PokemonListener, pokemon: Pokemon){
            binding.pokemon = pokemon
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PokemonListViewHolder(ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        ))

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
            getItem(position)?.let { holder.bind(clickListener, it) }
        }

    companion object DiffCallBack: DiffUtil.ItemCallback<Pokemon>(){
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) =  oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem == newItem
    }

    class PokemonListener(val clickListener: (pokemon: Pokemon) -> Unit){
        fun onClick(pokemon: Pokemon) = clickListener(pokemon)
    }

}