package com.orbitalsonic.chatgpt.helpers.api

import com.orbitalsonic.chatgpt.helpers.datamodel.ChatCompletionRequest
import com.orbitalsonic.chatgpt.helpers.datamodel.ChatCompletionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAIChatService {
    @POST("chat/completions")
    fun getChatCompletion(@Body requestBody: ChatCompletionRequest): Call<ChatCompletionResponse>
}
