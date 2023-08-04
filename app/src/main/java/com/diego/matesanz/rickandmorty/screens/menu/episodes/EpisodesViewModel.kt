package com.diego.matesanz.rickandmorty.screens.menu.episodes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.matesanz.rickandmorty.data.model.Episode
import com.diego.matesanz.rickandmorty.data.model.EpisodesResponse
import com.diego.matesanz.rickandmorty.data.repositories.EpisodesRepository
import com.diego.matesanz.rickandmorty.utils.ConstantsUtil
import kotlinx.coroutines.launch
import retrofit2.Response

class EpisodesViewModel : ViewModel() {

    val searchingText: MutableLiveData<String> = MutableLiveData("")
    val episodesResponse: MutableLiveData<Response<EpisodesResponse>?> = MutableLiveData()
    val episodesList: MutableLiveData<MutableList<Episode>> = MutableLiveData(mutableListOf())

    var isNewSearch = true
    var loadingEpisodes = false

    private val episodesRepository = EpisodesRepository()

    private var getNextUrl = true

    fun getEpisodes() {
        getNextUrl = true
        loadingEpisodes = true
        if (isNewSearch) {
            getNextUrl = false
        }
        var url = ConstantsUtil.RICK_AND_MORTY_BASE_URL + ConstantsUtil.EPISODES_PATH
        if (!getNextUrl) {
            if (searchingText.value?.isNotEmpty() == true) {
                url += "/?name=${searchingText.value}"
            }
        } else {
            if (!episodesResponse.value?.body()?.info?.next.isNullOrEmpty()) {
                url = episodesResponse.value?.body()?.info?.next!!
            }
        }
        Log.e(EpisodesFragment.TAG, "url: $url")
        viewModelScope.launch {
            try {
                val response = episodesRepository.getEpisodes(url)
                Log.e(EpisodesFragment.TAG, "response: ${response.body()}")
                episodesResponse.value = response
            } catch (e: Exception) {
                Log.e(EpisodesFragment.TAG, "Exception error = $e")
            }
        }
    }

    fun getEpisodesInfo() {
        if (isNewSearch) {
            episodesList.value = episodesResponse.value?.body()?.results as MutableList<Episode>
        } else {
            val auxEpisodes = episodesList.value
            auxEpisodes?.addAll(episodesResponse.value?.body()?.results as MutableList<Episode>)
            episodesList.value = auxEpisodes!!
        }
    }
}
