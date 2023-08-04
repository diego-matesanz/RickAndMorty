package com.diego.matesanz.rickandmorty.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.diego.matesanz.rickandmorty.screens.MainActivity

object PhoneUtil {

    fun hideKeyBoard(view: View) {
        val imm by lazy { MainActivity.instance.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
