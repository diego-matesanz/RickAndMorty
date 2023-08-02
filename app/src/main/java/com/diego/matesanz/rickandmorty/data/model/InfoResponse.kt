package com.diego.matesanz.rickandmorty.data.model

import com.diego.matesanz.rickandmorty.utils.ConstantsUtil
import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName(ConstantsUtil.COUNT_PARAM) val count: Int,
    @SerializedName(ConstantsUtil.PAGES_PARAM) val pages: Int,
    @SerializedName(ConstantsUtil.NEXT_PARAM) val next: String?,
    @SerializedName(ConstantsUtil.PREV_PARAM) val prev: String?
)
