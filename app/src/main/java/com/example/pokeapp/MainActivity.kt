package com.example.pokeapp

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val actionBar = supportActionBar
        actionBar?.displayOptions = DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(R.layout.toolbar_skin)
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.show()
    }

    fun toggleDarkMode(view: View) {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                setLightMode()
            }
            else -> {
                setDarkMode()
            }

        }
        overridePendingTransition(750, 750)
    }

    private fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun setLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}