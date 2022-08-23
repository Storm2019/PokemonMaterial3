package com.test.pokedex.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.pokedex.presentation.navigation.Screen
import kotlinx.coroutines.*

@OptIn(ExperimentalComposeUiApi::class)
@DelicateCoroutinesApi
class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    private val viewModel: PokemonListViewModel by viewModels()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.PokemonList.route) {
                composable(route = Screen.PokemonList.route) {
                    PokemonListScreen(
                        onNavigateToPokemonDetailScreen = navController,
                        viewModel = viewModel
                    )
                }
                composable(route = Screen.PokemonDetail.route) {
                    PokemonListScreen(
                        onNavigateToPokemonDetailScreen = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
