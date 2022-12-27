package com.example.pokeapp.adapters

import android.view.View.*
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pokeapp.R
import com.example.pokeapp.util.Constants.FETCH_STATUS
import com.example.pokeapp.util.Constants.IMG_URL
import com.example.pokeapp.viewmodels.HomeViewModel
import com.example.pokeapp.viewmodels.HomeViewModel.LoadingStates.*


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
