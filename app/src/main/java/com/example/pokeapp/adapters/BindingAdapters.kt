package com.example.pokeapp.adapters

import android.os.Build
import android.view.View.*
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pokeapp.R
import com.example.pokeapp.models.pokemonresponses.Type
import com.example.pokeapp.util.Constants.FETCH_STATUS
import com.example.pokeapp.util.Constants.HEIGHT
import com.example.pokeapp.util.Constants.IMG_URL
import com.example.pokeapp.util.Constants.MAX_EXP
import com.example.pokeapp.util.Constants.MAX_STAT
import com.example.pokeapp.util.Constants.POKEMON_TYPE_COLOR
import com.example.pokeapp.util.Constants.POKEMON_TYPE_IMAGE
import com.example.pokeapp.util.Constants.POKEMON_TYPE_IMAGE_2
import com.example.pokeapp.util.Constants.POKEMON_TYPE_TINT
import com.example.pokeapp.util.Constants.SET_EXP
import com.example.pokeapp.util.Constants.SET_STAT
import com.example.pokeapp.util.Constants.WEIGHT
import com.example.pokeapp.util.Constants.pokemonTypeColorsList
import com.example.pokeapp.util.Constants.pokemonTypeImagesList
import com.example.pokeapp.util.Constants.pokemonTypesList
import com.example.pokeapp.util.Constants.toKilograms
import com.example.pokeapp.util.Constants.toMeters
import com.example.pokeapp.viewmodels.HomeViewModel
import com.example.pokeapp.viewmodels.HomeViewModel.LoadingStates.*

object BindingAdapters {

    @JvmStatic
    @BindingAdapter(IMG_URL)
    fun bindImage(imageView: ImageView, imgUrl: String?) = imgUrl?.let { url ->
        Glide.with(imageView.context)
            .load(url)
            .thumbnail(.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade(250))
            .into(imageView)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    @BindingAdapter(POKEMON_TYPE_IMAGE)
    fun setPokemonImageByType(imageView: ImageView, name: String) {
        val index = pokemonTypesList.indexOf(name)
        imageView.setImageResource(
            pokemonTypeImagesList[index]
        )
        imageView.imageTintList = ContextCompat.getColorStateList(imageView.context, pokemonTypeColorsList[index])
        imageView.tooltipText = name
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    @BindingAdapter(POKEMON_TYPE_IMAGE_2)
    fun setPokemonImageByType(imageView: ImageView, types: List<Type>) = if (types.size > 1) {
        val index = pokemonTypesList.indexOf(types[1].type.name)
        imageView.setImageResource(pokemonTypeImagesList[index])
        imageView.imageTintList = ContextCompat.getColorStateList(imageView.context, pokemonTypeColorsList[index])
        imageView.tooltipText = types[1].type.name
    }
        else
            imageView.visibility = GONE



    @JvmStatic
    @BindingAdapter(POKEMON_TYPE_COLOR)
    fun setPokemonColorByType(card: CardView, name: String) =
        pokemonTypeColorsList[pokemonTypesList.indexOf(name)].also {
            val color = ContextCompat.getColorStateList(card.context, it)
            card.setCardBackgroundColor(color)
        }

    @JvmStatic
    @BindingAdapter(POKEMON_TYPE_TINT)
    fun setPokemonTintByType(progress: ProgressBar, name: String) =
        pokemonTypeColorsList[pokemonTypesList.indexOf(name)].also {
            progress.progressTintList = ContextCompat.getColorStateList(progress.context, it)
        }


    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    @BindingAdapter(SET_STAT)
    fun setPokemonStat(progress: ProgressBar, stat: Int) =
        progress.setProgress(stat).also {
            progress.max = MAX_STAT
            progress.tooltipText = "$stat / $MAX_STAT"
        }


    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    @BindingAdapter(SET_EXP)
    fun setPokemonExp(progress: ProgressBar, exp: Int) =
        progress.setProgress(exp).also {
            progress.max = MAX_EXP
            progress.tooltipText = "$exp / $MAX_EXP"
        }


    @JvmStatic
    @BindingAdapter(WEIGHT)
    fun setPokemonWeight(textView: TextView, weightInHectograms: Int) =
        "${weightInHectograms.toKilograms()} KG".also { textView.text = it }


    @JvmStatic
    @BindingAdapter(HEIGHT)
    fun setPokemonHeight(textView: TextView, heightInDecimetres: Int) =
        "${heightInDecimetres.toMeters()} M".also { textView.text = it }



    @JvmStatic
    @BindingAdapter(FETCH_STATUS)
    fun fetchStatus(imageView: ImageView, status: HomeViewModel.LoadingStates) = imageView.apply {
        visibility = when (status) {
            SUCCESS -> GONE
            LOADING -> VISIBLE.also { setImageResource(R.drawable.pokeball_animation) }
            ERROR -> VISIBLE.also { setImageResource(R.drawable.ic_error) }
        }
    }

}