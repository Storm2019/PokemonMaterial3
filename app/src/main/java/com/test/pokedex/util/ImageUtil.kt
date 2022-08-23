package com.test.pokedex.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.test.pokedex.R
import kotlinx.coroutines.ExperimentalCoroutinesApi


const val DEFAULT_POKEMON_IMAGE = R.drawable.empty_pokemon

@SuppressLint("UnrememberedMutableState")
@ExperimentalCoroutinesApi
@Composable
fun loadPicture(url: String, @DrawableRes defaultImage: Int): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    // show default image while image loads
//    Glide.with(LocalContext.current)
//        .asBitmap()
//        .load(defaultImage)
//        .into(object : CustomTarget<Bitmap>() {
//            override fun onLoadCleared(placeholder: Drawable?) { }
//            override fun onResourceReady(
//                resource: Bitmap,
//                transition: Transition<in Bitmap>?
//            ) {
//                bitmapState.value = resource
//            }
//        })

    // get network image
    Glide.with(LocalContext.current)
        .asBitmap()
        .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$20.svg")
        .dontAnimate()
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) { }
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })

    return bitmapState
}