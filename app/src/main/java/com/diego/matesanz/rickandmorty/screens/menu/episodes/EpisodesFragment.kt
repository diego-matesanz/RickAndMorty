package com.diego.matesanz.rickandmorty.screens.menu.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diego.matesanz.rickandmorty.databinding.FragmentEpisodesBinding

class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(layoutInflater)

        return binding.root
    }

    companion object {
        private val TAG = EpisodesFragment::class.java.simpleName
    }
}
