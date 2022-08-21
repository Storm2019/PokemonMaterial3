package com.test.pokedex.api

import com.test.pokedex.response.Pokemon
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

//    @GET ("pokemon/?limit=400&offset=60")
    @GET ("pokemon/")
    suspend fun getPokemon() : Response<Pokemon>
}