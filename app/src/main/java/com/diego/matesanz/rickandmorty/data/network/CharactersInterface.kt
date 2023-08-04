package com.diego.matesanz.rickandmorty.data.network

import com.diego.matesanz.rickandmorty.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface CharactersInterface {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET
    suspend fun getCharacters(@Url url: String): Response<CharactersResponse>
}

