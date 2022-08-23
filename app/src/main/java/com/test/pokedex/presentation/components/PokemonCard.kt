package com.test.pokedex.presentation.components

import android.R
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.*
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.request.ImageRequest
import coil.size.Size
import com.test.pokedex.util.DEFAULT_POKEMON_IMAGE
import com.test.pokedex.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonCard(
    id: String,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    Log.d("bobo", "PokemonCard: $url")
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally

        ) {
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png")
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                            .transformations(
                            )
                            .build()
                    }).build()
            )

            val state = painter.state
            Log.d("bobo2", "PokemonCard: $state")

            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier.padding(16.dp)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center,

                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.scale(1f)
                    )
                }
            }

//            viewModel.getImageBackgroundColor(entry.imageUrl, LocalContext.current) { color ->
//                dominantColor = color
//            }

            Image(
                painter = painter,
                contentDescription = title,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            )


//            val painter = rememberAsyncImagePainter(
//                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$id.svg"
////                "https://picsum.photos/seed/${Random.nextInt()}/300/200"
//            )
//
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png")
//                    .crossfade(true)
//                    .build(),
//                contentDescription = null,
//                contentScale = ContentScale.FillWidth,
//                modifier = modifier.clip(CircleShape)
//            )
//
//
//            SubcomposeAsyncImage(
//                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
////                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/$id.gif",
//                contentDescription = null,
//                modifier = Modifier.fillMaxSize(),
//            ) {
//                val state = painter.state
//                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
//                    Box(
//                        modifier = Modifier.padding(16.dp)
//                            .fillMaxHeight(),
//                        contentAlignment = Alignment.BottomCenter,
//                    ) {
//                        CircularProgressIndicator()
//                    }
//                } else {
//                    SubcomposeAsyncImageContent()
//                }
//        }
            Column(
                modifier = modifier.padding(16.dp)
            ) {
                Text(
                    text = title.replaceFirstChar { it.uppercaseChar() },
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
