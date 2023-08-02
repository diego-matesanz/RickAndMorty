package com.diego.matesanz.rickandmorty.data.network

import com.diego.matesanz.rickandmorty.data.model.CharactersResponse
import com.diego.matesanz.rickandmorty.utils.ConstantsUtil
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface CharactersInterface {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET(ConstantsUtil.CHARACTERS_PATH)
    suspend fun getCharacters(): Response<CharactersResponse>
}

