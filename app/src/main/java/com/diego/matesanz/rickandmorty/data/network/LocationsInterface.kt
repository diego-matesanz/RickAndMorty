package com.diego.matesanz.rickandmorty.data.network

import com.diego.matesanz.rickandmorty.data.model.LocationsResponse
import com.diego.matesanz.rickandmorty.utils.ConstantsUtil
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface LocationsInterface {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET(ConstantsUtil.LOCATIONS_PATH)
    suspend fun getLocations(): Response<LocationsResponse>
}
