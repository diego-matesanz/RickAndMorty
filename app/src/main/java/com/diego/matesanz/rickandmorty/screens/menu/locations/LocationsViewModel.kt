package com.diego.matesanz.rickandmorty.screens.menu.locations

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.matesanz.rickandmorty.data.model.Location
import com.diego.matesanz.rickandmorty.data.model.LocationsResponse
import com.diego.matesanz.rickandmorty.data.repositories.LocationsRepository
import com.diego.matesanz.rickandmorty.utils.ConstantsUtil
import kotlinx.coroutines.launch
import retrofit2.Response

class LocationsViewModel : ViewModel() {

    val searchingText: MutableLiveData<String> = MutableLiveData("")
    val locationsResponse: MutableLiveData<Response<LocationsResponse>?> = MutableLiveData()
    val locationList: MutableLiveData<MutableList<Location>> = MutableLiveData(mutableListOf())

    var isNewSearch = true
    var loadingLocations = false

    private val locationsRepository = LocationsRepository()

    private var getNextUrl = true

    fun getLocations() {
        getNextUrl = true
        loadingLocations = true
        if (isNewSearch) {
            getNextUrl = false
        }
        var url = ConstantsUtil.RICK_AND_MORTY_BASE_URL + ConstantsUtil.LOCATIONS_PATH
        if (!getNextUrl) {
            if (searchingText.value?.isNotEmpty() == true) {
                url += "/?name=${searchingText.value}"
            }
        } else {
            if (!locationsResponse.value?.body()?.info?.next.isNullOrEmpty()) {
                url = locationsResponse.value?.body()?.info?.next!!
            }
        }
        Log.e(LocationsFragment.TAG, "url: $url")
        viewModelScope.launch {
            try {
                val response = locationsRepository.getLocations(url)
                Log.e(LocationsFragment.TAG, "response: ${response.body()}")
                locationsResponse.value = response
            } catch (e: Exception) {
                Log.e(LocationsFragment.TAG, "Exception error = $e")
            }
        }
    }

    fun getLocationsInfo() {
        if (isNewSearch) {
            locationList.value = locationsResponse.value?.body()?.results as MutableList<Location>
        } else {
            val auxLocations = locationList.value
            auxLocations?.addAll(locationsResponse.value?.body()?.results as MutableList<Location>)
            locationList.value = auxLocations!!
        }
    }
}
