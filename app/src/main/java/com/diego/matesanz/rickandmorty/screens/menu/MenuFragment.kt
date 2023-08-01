package com.diego.matesanz.rickandmorty.screens.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.diego.matesanz.rickandmorty.R
import com.diego.matesanz.rickandmorty.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater)

        initBottomBar()

        return binding.root
    }

    private fun initBottomBar() {
        navHostFragment =
            childFragmentManager.findFragmentById(R.id.menu_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.menuBottomNavigationView.setupWithNavController(navController)
    }

    companion object {
        private val TAG = MenuFragment::class.java.simpleName
    }
}
