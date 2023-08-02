package com.diego.matesanz.rickandmorty.data.repositories

import com.diego.matesanz.rickandmorty.data.model.CharactersResponse
import com.diego.matesanz.rickandmorty.data.network.CharactersInterface
import com.diego.matesanz.rickandmorty.utils.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CharactersRepository {

    suspend fun getCharacters(): Response<CharactersResponse> {
        return withContext(Dispatchers.IO) {
            RetrofitUtil.instance.create(CharactersInterface::class.java).getCharacters()
        }
    }
}
