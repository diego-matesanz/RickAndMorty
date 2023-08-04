package com.diego.matesanz.rickandmorty.screens.menu.characters

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.diego.matesanz.rickandmorty.R
import com.diego.matesanz.rickandmorty.data.model.Character
import com.diego.matesanz.rickandmorty.databinding.FragmentCharactersBinding
import com.diego.matesanz.rickandmorty.interfaces.OnCardClickListener
import com.diego.matesanz.rickandmorty.screens.menu.characters.adapters.CharacterItemAdapter
import com.diego.matesanz.rickandmorty.utils.PhoneUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputLayout

class CharactersFragment : Fragment(), OnCardClickListener {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var characterGridItemAdapter: CharacterItemAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val viewModel: CharactersViewModel by activityViewModels()

    private var isInfoVisible = false
    private var isFirstCharactersLoad = true
    private var timer: Handler = Handler(Looper.getMainLooper())

    override fun onCardClicked(data: Any) {
        if (data is Character) {
            binding.editTextSearch.clearFocus()
            requireView().requestFocus()
            PhoneUtil.hideKeyBoard(requireView())
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
        binding.isLoading = true
        binding.viewModel = viewModel
        binding.isEmptyResults = false
        isFirstCharactersLoad = true
        viewModel.isNewSearch = true
        viewModel.getCharacters()

        setListeners()
        setObservers()
        initGifs()
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
        binding.editTextSearch.doOnTextChanged { _, _, _, _ ->
            viewModel.searchingText.value = binding.editTextSearch.text.toString()
            if (viewModel.searchingText.value?.isEmpty() == true) {
                binding.textInputLayoutSearch.endIconMode = TextInputLayout.END_ICON_NONE
                binding.textInputLayoutSearch.endIconDrawable = null
            } else {
                binding.textInputLayoutSearch.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
                binding.textInputLayoutSearch.endIconDrawable = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.baseline_clear_24
                )
            }
            timer.removeCallbacksAndMessages(null)
            timer.postDelayed({
                binding.isLoading = true
                viewModel.isNewSearch = true
                viewModel.getCharacters()
            }, 500)
        }
        binding.editTextSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (viewModel.searchingText.value?.isEmpty() == true) {
                    binding.isLoading = true
                    viewModel.isNewSearch = true
                    viewModel.getCharacters()
                    PhoneUtil.hideKeyBoard(requireView())
                }
            }
            true
        }
    }

    private fun setObservers() {
        viewModel.charactersResponse.observe(viewLifecycleOwner) { response ->
            binding.isLoading = false
            if (response != null) {
                if (response.body()?.results?.isNotEmpty() == true) {
                    binding.isEmptyResults = false
                    viewModel.loadingCharacters = false
                    viewModel.getCharactersInfo()
                    if (isFirstCharactersLoad) {
                        initRecyclerView()
                        isFirstCharactersLoad = false
                    }
                } else {
                    binding.isEmptyResults = true
                }
                if (viewModel.isNewSearch) {
                    viewModel.isNewSearch = false
                }
            } else {
                binding.isEmptyResults = true
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

    private fun initGifs() {
        Glide.with(this).load(R.drawable.rick_and_morty_error).into(binding.errorGif)
        Glide.with(this).load(R.drawable.rick_and_morty_loader).into(binding.layoutLoader.loadingGif)
    }

    companion object {
        val TAG: String = CharactersFragment::class.java.simpleName

        const val CHARACTERS_GRID_SPAN_COUNT = 2
    }
}
