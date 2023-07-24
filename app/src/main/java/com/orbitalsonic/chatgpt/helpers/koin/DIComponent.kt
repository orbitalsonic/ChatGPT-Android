package com.orbitalsonic.chatgpt.helpers.koin

import com.orbitalsonic.chatgpt.helpers.api.OpenApiClient
import com.orbitalsonic.chatgpt.helpers.managers.InternetManager
import com.orbitalsonic.chatgpt.helpers.preferences.SharedPreferenceUtils
import com.orbitalsonic.chatgpt.roomdb.viewmodel.AiChatViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DIComponent : KoinComponent {

    // Utils
    val sharedPreferenceUtils by inject<SharedPreferenceUtils>()

    // Managers
    val internetManager by inject<InternetManager>()

    // Db ViewModel
    val aiChatViewModel by inject<AiChatViewModel>()

    val openApiClient by inject<OpenApiClient>()

}