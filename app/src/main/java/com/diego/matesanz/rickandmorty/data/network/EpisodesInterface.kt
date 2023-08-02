package com.diego.matesanz.rickandmorty.data.network

import com.diego.matesanz.rickandmorty.data.model.EpisodesResponse
import com.diego.matesanz.rickandmorty.utils.ConstantsUtil
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface EpisodesInterface {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET(ConstantsUtil.EPISODES_PATH)
    suspend fun getEpisodes(): Response<EpisodesResponse>
}
