package com.diego.matesanz.rickandmorty.screens

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import com.diego.matesanz.rickandmorty.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            WindowInsetsControllerCompat(window, decorView).isAppearanceLightStatusBars = true
            statusBarColor = Color.TRANSPARENT
        }
    }
}
