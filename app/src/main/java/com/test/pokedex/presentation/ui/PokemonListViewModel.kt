package com.test.pokedex.presentation.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.pokedex.api.ApiInterface
import com.test.pokedex.api.RetrofitHelper
import com.test.pokedex.response.Pokemon
import com.test.pokedex.response.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PokemonListViewModel : ViewModel() {
    val pokemon: MutableState<List<Result>> = mutableStateOf(ArrayList())

    init {
        getPokemon()
    }
    fun getPokemon() {

        val api = RetrofitHelper.getInstance().create(ApiInterface::class.java)

        viewModelScope.launch {
            val results = api.getPokemon().body()!!.results
            pokemon.value = results
        }
    }
}