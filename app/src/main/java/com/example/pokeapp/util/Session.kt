package com.example.pokeapp.util

import android.content.Context
import androidx.preference.PreferenceManager

class Session(private val context: Context) {
    private val pref = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor = pref.edit()

    fun setLightDarkMode(isDark: Boolean){
        editor.putBoolean("isDark", isDark).commit()
    }

    fun getDarkLightMode():Boolean{
        return pref.getBoolean("isDark", true)
    }
}