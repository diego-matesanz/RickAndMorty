package com.diego.matesanz.rickandmorty.screens.menu.characters

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.matesanz.rickandmorty.data.model.Character
import com.diego.matesanz.rickandmorty.data.model.CharactersResponse
import com.diego.matesanz.rickandmorty.data.repositories.CharactersRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel : ViewModel() {

    val charactersResponse: MutableLiveData<Response<CharactersResponse>> = MutableLiveData()
    val characterList: MutableLiveData<MutableList<Character>> = MutableLiveData(mutableListOf())

    private val charactersRepository = CharactersRepository()

    private var isNewSearch = true

    fun getCharacters() {
        Log.e(CharactersFragment.TAG, "getCharacters")
        viewModelScope.launch {
            try {
                val response = charactersRepository.getCharacters()
                Log.e(CharactersFragment.TAG, "response = $response")
                if (response.isSuccessful) {
                    charactersResponse.value = response
                }
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
