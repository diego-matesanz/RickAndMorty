package com.diego.matesanz.rickandmorty.data.repositories

import com.diego.matesanz.rickandmorty.data.model.EpisodesResponse
import com.diego.matesanz.rickandmorty.data.network.EpisodesInterface
import com.diego.matesanz.rickandmorty.utils.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class EpisodesRepository {

    suspend fun getEpisodes(url: String): Response<EpisodesResponse> {
        return withContext(Dispatchers.IO) {
            RetrofitUtil.instance.create(EpisodesInterface::class.java).getEpisodes(url)
        }
    }
}
