package com.test.pokedex.presentation.ui

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.test.pokedex.presentation.components.PokemonCard
import com.test.pokedex.presentation.navigation.Screen
import com.test.pokedex.ui.theme.Material3AppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@Composable
fun PokemonListScreen (
    onNavigateToPokemonDetailScreen: NavController,
    viewModel: PokemonListViewModel,
        ) {
    val pokemons = viewModel.pokemon.value
    val TAG = "PokemonListScreen"


    Material3AppTheme() {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    SmallTopAppBar(
                        title = {
                            Text(text = "Pokemon Material 3 Test")
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            ) { values ->

                for (x in pokemons) {
                    Log.d(TAG, "onCreate: $x" )
                }
                LazyColumn(
                    contentPadding = values
                ) {

                    itemsIndexed(
                        items = pokemons
                    ) { index, _ ->


                        val id = when (pokemons[index].url.length) {
                            36 -> pokemons[index].url.substring(34,35)
                            37 -> pokemons[index].url.substring(34,36)
                            38 ->  pokemons[index].url.substring(34,37)
                            else -> {
                                pokemons[index].url.substring(34,38)
                            }
                        }
                        PokemonCard(
                            id = id,
                            title = pokemons[index].name,
                            onClick = {

                                onNavigateToPokemonDetailScreen.navigate(Screen.PokemonDetail.route)

                            },
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}
