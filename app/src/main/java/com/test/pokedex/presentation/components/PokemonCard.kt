package com.test.pokedex.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.test.pokedex.util.DEFAULT_POKEMON_IMAGE
import com.test.pokedex.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
fun PokemonCard(
    id: String,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,

        ),
        modifier = modifier
//            .padding(
//                bottom = 6.dp,
//                top = 6.dp,
//            )
//            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column() {
            val url2 = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$id.svg"
            val url = "https://picsum.photos/seed/${Random.nextInt()}/300/200"
            Log.d("chuchu", "PokemonCard: $url")
            val image = loadPicture(
                url = url,
                defaultImage = DEFAULT_POKEMON_IMAGE
            ).value

            image?.let { img ->


                Image(
                    bitmap = img.asImageBitmap(),
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.large)
                        .fillMaxWidth()
                        .aspectRatio(3f / 2f),
                    contentDescription = null

                )

//                Icon(
//                painter = painterResource(id = R..empty_pokemon),
//                val painter = rememberImagePainter(
//                    data = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$id.svg",
////                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$id.svg"
////                    model = "https://picsum.photos/seed/${Random.nextInt()}/300/200"
//                ),
//
//
//                    contentDescription = null,
//                    modifier = modifier
//                        .clip(MaterialTheme.shapes.large)
//                        .fillMaxWidth()
//                        .aspectRatio(3f / 2f)
//                )
            }
            Column(
                modifier = modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}
