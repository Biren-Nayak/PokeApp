package com.example.pokeapp.adapters

import android.view.View.*
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapp.R
import com.example.pokeapp.models.PokemonEntry
import com.example.pokeapp.ui.viewmodels.HomeViewModel
import com.example.pokeapp.ui.viewmodels.HomeViewModel.LoadingStates.*
import com.example.pokeapp.util.Constants.DATA
import com.example.pokeapp.util.Constants.FETCH_STATUS
import com.example.pokeapp.util.Constants.IMG_URL

@BindingAdapter(DATA)
fun bindData(recyclerView: RecyclerView, data: List<PokemonEntry>?){
    val adapter = recyclerView.adapter as PokemonListAdapter
    adapter.submitList(data)
}


@BindingAdapter(IMG_URL)
fun bindImage(imageView: ImageView, imgUrl: String?){
    imgUrl?.let {
        Glide.with(imageView.context)
            .load(imgUrl)
            .into(imageView)
    }
}


@BindingAdapter(FETCH_STATUS)
fun fetchStatus(imageView: ImageView, status: HomeViewModel.LoadingStates){
    when(status){
        SUCCESS -> imageView.visibility = GONE

        LOADING -> {
            imageView.visibility = VISIBLE
            imageView.setImageResource(R.drawable.pokeball_animation)
        }

        ERROR -> {
            imageView.visibility = VISIBLE
            imageView.setImageResource(R.drawable.ic_error)
        }
    }
}
