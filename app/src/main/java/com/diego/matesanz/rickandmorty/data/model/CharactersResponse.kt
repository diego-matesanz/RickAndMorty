package com.diego.matesanz.rickandmorty.data.model

import com.diego.matesanz.rickandmorty.utils.ConstantsUtil
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName(ConstantsUtil.INFO_PARAM) val info: InfoResponse,
    @SerializedName(ConstantsUtil.RESULTS_PARAM) val results: List<Character>
)

data class Character(
    @SerializedName(ConstantsUtil.ID_PARAM) val id: Int,
    @SerializedName(ConstantsUtil.NAME_PARAM) val name: String,
    @SerializedName(ConstantsUtil.STATUS_PARAM) val status: String,
    @SerializedName(ConstantsUtil.SPECIES_PARAM) val species: String,
    @SerializedName(ConstantsUtil.TYPE_PARAM) val type: String,
    @SerializedName(ConstantsUtil.GENDER_PARAM) val gender: String,
    @SerializedName(ConstantsUtil.ORIGIN_PARAM) val origin: SimpleLocation,
    @SerializedName(ConstantsUtil.LOCATION_PARAM) val location: SimpleLocation,
    @SerializedName(ConstantsUtil.IMAGE_PARAM) val image: String,
    @SerializedName(ConstantsUtil.EPISODE_PARAM) val episode: List<String>,
    @SerializedName(ConstantsUtil.URL_PARAM) val url: String,
    @SerializedName(ConstantsUtil.CREATED_PARAM) val created: String
)
