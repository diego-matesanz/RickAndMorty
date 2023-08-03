package com.diego.matesanz.rickandmorty.utils

import androidx.window.layout.WindowMetricsCalculator
import com.diego.matesanz.rickandmorty.screens.MainActivity

object PhoneUtil {

    fun getScreenHeight(): Int {
        val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(MainActivity.instance)
        val currentBounds = windowMetrics.bounds
        return currentBounds.height()
    }
}
