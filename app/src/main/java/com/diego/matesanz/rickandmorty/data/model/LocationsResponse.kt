package com.diego.matesanz.rickandmorty.data.model

import com.diego.matesanz.rickandmorty.utils.ConstantsUtil
import com.google.gson.annotations.SerializedName

data class LocationsResponse(
    @SerializedName(ConstantsUtil.INFO_PARAM) val info: InfoResponse,
    @SerializedName(ConstantsUtil.RESULTS_PARAM) val results: List<Location>
)

data class Location(
    @SerializedName(ConstantsUtil.ID_PARAM) val id: Int,
    @SerializedName(ConstantsUtil.NAME_PARAM) val name: String,
    @SerializedName(ConstantsUtil.TYPE_PARAM) val type: String,
    @SerializedName(ConstantsUtil.DIMENSION_PARAM) val dimension: String,
    @SerializedName(ConstantsUtil.RESIDENTS_PARAM) val residents: List<String>,
    @SerializedName(ConstantsUtil.URL_PARAM) val url: String,
    @SerializedName(ConstantsUtil.CREATED_PARAM) val created: String
)

data class SimpleLocation(
    @SerializedName(ConstantsUtil.NAME_PARAM) val name: String,
    @SerializedName(ConstantsUtil.URL_PARAM) val url: String
)
