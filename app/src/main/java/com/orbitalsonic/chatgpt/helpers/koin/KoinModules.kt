package com.orbitalsonic.chatgpt.helpers.koin

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.orbitalsonic.chatgpt.helpers.api.OpenApiClient
import com.orbitalsonic.chatgpt.helpers.managers.InternetManager
import com.orbitalsonic.chatgpt.helpers.preferences.SharedPreferenceUtils
import com.orbitalsonic.chatgpt.roomdb.db.AiChatDatabase
import com.orbitalsonic.chatgpt.roomdb.repository.AiChatRepository
import com.orbitalsonic.chatgpt.roomdb.viewmodel.AiChatViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val applicationScope = CoroutineScope(SupervisorJob())

private val dbModule = module {
    single { AiChatDatabase.getDatabase(androidContext(), applicationScope).aiChatDao() }

    single { AiChatRepository(get()) }

    single { AiChatViewModel(get()) }
}

private val managerModules = module {
    single { InternetManager(androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager) }
}

private val utilsModules = module {
    single {
        SharedPreferenceUtils(
            androidContext().getSharedPreferences(
                "app_preferences",
                Application.MODE_PRIVATE
            )
        )
    }
}
private val networksModule = module {
    single { OpenApiClient() }
}


val modulesList = listOf(utilsModules, managerModules,networksModule, dbModule)