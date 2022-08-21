@file:OptIn(ExperimentalMaterial3Api::class)

package com.test.pokedex.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.substring
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.pokedex.api.ApiInterface
import com.test.pokedex.api.RetrofitHelper
import com.test.pokedex.ui.theme.Material3AppTheme
import kotlin.math.log
import com.test.pokedex.presentation.components.PokemonCard
import com.test.pokedex.response.Pokemon
import com.test.pokedex.response.Result
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Runnable

@DelicateCoroutinesApi
class MainActivity : ComponentActivity() {
    private var _path: Response<Pokemon>? = null
    val TAG = "MainActivity"
    private val viewModel: PokemonListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                                    Text(text = "Material 3")
                                },
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                    ) { values ->
                        val pokemons = viewModel.pokemon.value

                        for (x in pokemons) {
                            Log.d(TAG, "onCreate: $x" )
                        }
                        LazyColumn(
                            contentPadding = values
                        ) {

                            itemsIndexed(
                                items = pokemons
                            ) { index, _ ->
                                val id = if (pokemons[index].url.length == 36) {
                                    pokemons[index].url.substring(34,35)
                                } else {
                                    pokemons[index].url.substring(34,36)
                                }
                                PokemonCard(
                                    id = id,
                                    title = pokemons[index].name,
                                    onClick = { },
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }


        //launching courotine

    }
}
