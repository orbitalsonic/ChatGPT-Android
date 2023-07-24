package com.orbitalsonic.chatgpt

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.orbitalsonic.chatgpt.helpers.api.OpenAiInitializer
import com.orbitalsonic.chatgpt.helpers.koin.modulesList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        OpenAiInitializer.initialize(getString(R.string.chat_gpt_api_key))
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(modulesList)
        }
    }
}