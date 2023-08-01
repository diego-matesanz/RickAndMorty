package com.diego.matesanz.rickandmorty.screens.menu.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diego.matesanz.rickandmorty.databinding.FragmentLocationsBinding

class LocationsFragment : Fragment() {

    private lateinit var binding: FragmentLocationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(layoutInflater)

        return binding.root
    }

    companion object {
        private val TAG = LocationsFragment::class.java.simpleName
    }
}