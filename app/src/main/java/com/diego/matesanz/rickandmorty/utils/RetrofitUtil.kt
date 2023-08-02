package com.diego.matesanz.rickandmorty.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {

    val instance by lazy { getRetrofit() }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ConstantsUtil.RICK_AND_MORTY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
