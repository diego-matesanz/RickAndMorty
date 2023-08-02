package com.diego.matesanz.rickandmorty.screens.menu.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.diego.matesanz.rickandmorty.databinding.FragmentCharactersBinding
import com.diego.matesanz.rickandmorty.screens.menu.characters.adapters.CharacterItemAdapter

class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var characterGridItemAdapter: CharacterItemAdapter

    private val viewModel: CharactersViewModel by activityViewModels()

    private var isFirstCharactersLoad = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater)
        viewModel.getCharacters()

        setObservers()

        return binding.root
    }

    private fun setObservers() {
        viewModel.charactersResponse.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                if (response.body()?.results?.isNotEmpty() == true) {
                    viewModel.getCharactersInfo()
                    if (isFirstCharactersLoad) {
                        initRecyclerView()
                        isFirstCharactersLoad = false
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        val characters = viewModel.characterList.value!!.map { it.copy() }
        characterGridItemAdapter = CharacterItemAdapter(characters)
        binding.recyclerViewCharacters.apply {
            layoutManager = GridLayoutManager(context, CHARACTERS_GRID_SPAN_COUNT)
            adapter = characterGridItemAdapter
        }
    }

    companion object {
        val TAG: String = CharactersFragment::class.java.simpleName

        const val CHARACTERS_GRID_SPAN_COUNT = 2
    }
}
