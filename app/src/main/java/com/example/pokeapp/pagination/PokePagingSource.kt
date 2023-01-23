package com.example.pokeapp.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.pokeapp.models.pokemonresponses.Pokemon
import com.example.pokeapp.repository.PokeRepository
import com.example.pokeapp.util.Constants.STARTING_KEY
import com.example.pokeapp.util.Constants.limit
import java.io.IOException
import kotlin.math.max

class PokePagingSource(private val repository: PokeRepository): PagingSource<Int, Pokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val pageIndex = params.key ?: STARTING_KEY
        val range = pageIndex.until(pageIndex + params.loadSize)
        return try {
            val pokeResults = repository.getPokemonList(pageIndex, limit).results
            val pokemonList = repository.toPokemonList(pokeResults)

            val nextKey = range.last + 1
            LoadResult.Page(
                data = pokemonList,
                prevKey = if (pageIndex == STARTING_KEY) null else ensureValidKey(range.first - params.loadSize),
                nextKey = nextKey
            )
        }catch (exception: IOException){
            return LoadResult.Error(exception)
        }catch (exception: HttpException){
            return LoadResult.Error(exception)
        }



    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition) ?.nextKey?.minus(1)
        }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}