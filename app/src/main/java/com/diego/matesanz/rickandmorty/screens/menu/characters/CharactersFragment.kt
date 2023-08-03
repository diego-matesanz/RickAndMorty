package com.diego.matesanz.rickandmorty.screens.menu.characters

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.diego.matesanz.rickandmorty.data.model.Character
import com.diego.matesanz.rickandmorty.databinding.FragmentCharactersBinding
import com.diego.matesanz.rickandmorty.interfaces.OnCardClickListener
import com.diego.matesanz.rickandmorty.screens.menu.characters.adapters.CharacterItemAdapter
import com.diego.matesanz.rickandmorty.utils.PhoneUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior

class CharactersFragment : Fragment(), OnCardClickListener {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var characterGridItemAdapter: CharacterItemAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val viewModel: CharactersViewModel by activityViewModels()

    private var isInfoVisible = false
    private var isFirstCharactersLoad = true

    override fun onCardClicked(data: Any) {
        if (data is Character) {
            binding.selectedCharacter = data
            toggleInfoVisibility()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater)
        binding.fragment = this
        binding.showInfo = false
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getCharacters()

        setListeners()
        setObservers()
        initBottomSheet()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBackPressed()
    }

    fun toggleInfoVisibility() {
        isInfoVisible = !isInfoVisible
        if (isInfoVisible) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }
        binding.showInfo = isInfoVisible
    }

    private fun setListeners() {
        binding.scrollContainer.viewTreeObserver.addOnScrollChangedListener {
            val view = binding.scrollContainer.getChildAt(binding.scrollContainer.childCount - 1) as View
            val diff: Int = view.bottom - (binding.scrollContainer.height + binding.scrollContainer.scrollY)
            if (diff in 2001..2099) {
                if (!viewModel.loadingCharacters && this::characterGridItemAdapter.isInitialized) {
                    viewModel.getCharacters()
                }
            }
        }
    }

    private fun setObservers() {
        viewModel.charactersResponse.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                if (response.body()?.results?.isNotEmpty() == true) {
                    viewModel.loadingCharacters = false
                    viewModel.getCharactersInfo()
                    if (isFirstCharactersLoad) {
                        initRecyclerView()
                        isFirstCharactersLoad = false
                    }
                }
                if (viewModel.isNewSearch) {
                    viewModel.isNewSearch = false
                }
            }
        }
    }

    private fun initRecyclerView() {
        val characters = viewModel.characterList.value!!.map { it.copy() } as MutableList<Character>
        characterGridItemAdapter = CharacterItemAdapter(characters, this)
        binding.recyclerViewCharacters.apply {
            layoutManager = GridLayoutManager(context, CHARACTERS_GRID_SPAN_COUNT)
            adapter = characterGridItemAdapter
        }
    }

    private fun initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.layoutCharacterInfo.infoContainer)
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.skipCollapsed = true
        bottomSheetBehavior.maxHeight = (PhoneUtil.getScreenHeight() * 0.75).toInt()
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        isInfoVisible = false
                        binding.showInfo = isInfoVisible
                    }

                    else -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    private fun setOnBackPressed() {
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                if (isInfoVisible) toggleInfoVisibility()
                true
            } else false
        }
    }

    companion object {
        val TAG: String = CharactersFragment::class.java.simpleName

        const val CHARACTERS_GRID_SPAN_COUNT = 2
    }
}
