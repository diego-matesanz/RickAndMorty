package com.diego.matesanz.rickandmorty.screens.menu.characters

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.matesanz.rickandmorty.data.model.Character
import com.diego.matesanz.rickandmorty.data.model.CharactersResponse
import com.diego.matesanz.rickandmorty.data.repositories.CharactersRepository
import com.diego.matesanz.rickandmorty.utils.ConstantsUtil
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel : ViewModel() {

    val searchingText: MutableLiveData<String> = MutableLiveData("")
    val charactersResponse: MutableLiveData<Response<CharactersResponse>?> = MutableLiveData()
    val characterList: MutableLiveData<MutableList<Character>> = MutableLiveData(mutableListOf())

    var getNextUrl = true
    var isNewSearch = true
    var loadingCharacters = false

    private val charactersRepository = CharactersRepository()

    fun getCharacters() {
        getNextUrl = true
        loadingCharacters = true
        if (isNewSearch) {
            getNextUrl = false
        }
        var url = ConstantsUtil.RICK_AND_MORTY_BASE_URL + ConstantsUtil.CHARACTERS_PATH
        if (!getNextUrl) {
            if (searchingText.value?.isNotEmpty() == true) {
                url += "/?name=${searchingText.value}"
            }
        } else {
            if (!charactersResponse.value?.body()?.info?.next.isNullOrEmpty()) {
                url = charactersResponse.value?.body()?.info?.next!!
            }
        }
        Log.e(CharactersFragment.TAG, "url: $url")
        viewModelScope.launch {
            try {
                val response = charactersRepository.getCharacters(url)
                Log.e(CharactersFragment.TAG, "response: ${response.body()}")
                charactersResponse.value = response
            } catch (e: Exception) {
                Log.e(CharactersFragment.TAG, "Exception error = $e")
            }
        }
    }

    fun getCharactersInfo() {
        if (isNewSearch) {
            characterList.value = charactersResponse.value?.body()?.results as MutableList<Character>
        } else {
            val auxCharacters = characterList.value
            auxCharacters?.addAll(charactersResponse.value?.body()?.results as MutableList<Character>)
            characterList.value = auxCharacters!!
        }
    }
}
