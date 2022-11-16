package com.example.pokeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.adapters.PokemonListAdapter.PokemonListViewHolder
import com.example.pokeapp.databinding.ItemPokemonBinding
import com.example.pokeapp.models.PokemonEntry


class PokemonListAdapter(private val clickListener: PokemonListener) :ListAdapter<PokemonEntry, PokemonListViewHolder>(DiffCallBack) {

    class PokemonListViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: PokemonListener, pokemon: PokemonEntry){
            binding.pokemon = pokemon
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PokemonListViewHolder(ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        ))

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) = holder.bind( clickListener, getItem(position) )

    companion object DiffCallBack: DiffUtil.ItemCallback<PokemonEntry>(){
        override fun areItemsTheSame(oldItem: PokemonEntry, newItem: PokemonEntry) =  oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: PokemonEntry, newItem: PokemonEntry) = oldItem == newItem
    }

    class PokemonListener(val clickListener: (pokemon: PokemonEntry) -> Unit){
        fun onClick(pokemon: PokemonEntry) = clickListener(pokemon)
    }

}