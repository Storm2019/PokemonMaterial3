package com.test.pokedex.presentation.navigation

sealed class Screen(
    val route: String,
){
    object PokemonList: Screen("pokemonList")

    object PokemonDetail: Screen("pokemonDetail")
}