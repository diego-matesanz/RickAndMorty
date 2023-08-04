package com.diego.matesanz.rickandmorty.data.repositories

import com.diego.matesanz.rickandmorty.data.model.LocationsResponse
import com.diego.matesanz.rickandmorty.data.network.LocationsInterface
import com.diego.matesanz.rickandmorty.utils.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LocationsRepository {

    suspend fun getLocations(url: String): Response<LocationsResponse> {
        return withContext(Dispatchers.IO) {
            RetrofitUtil.instance.create(LocationsInterface::class.java).getLocations(url)
        }
    }
}
