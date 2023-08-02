package com.diego.matesanz.rickandmorty.data.model

import com.diego.matesanz.rickandmorty.utils.ConstantsUtil
import com.google.gson.annotations.SerializedName

data class EpisodesResponse(
    @SerializedName(ConstantsUtil.INFO_PARAM) val info: InfoResponse,
    @SerializedName(ConstantsUtil.RESULTS_PARAM) val results: List<Episode>
)

data class Episode(
    @SerializedName(ConstantsUtil.ID_PARAM) val id: Int,
    @SerializedName(ConstantsUtil.NAME_PARAM) val name: String,
    @SerializedName(ConstantsUtil.AIR_DATE_PARAM) val airDate: String,
    @SerializedName(ConstantsUtil.EPISODE_PARAM) val episode: String,
    @SerializedName(ConstantsUtil.CHARACTERS_PARAM) val characters: List<String>,
    @SerializedName(ConstantsUtil.URL_PARAM) val url: String,
    @SerializedName(ConstantsUtil.CREATED_PARAM) val created: String
)
