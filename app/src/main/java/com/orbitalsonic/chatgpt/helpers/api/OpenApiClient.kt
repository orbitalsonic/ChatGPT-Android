package com.orbitalsonic.chatgpt.helpers.api

import com.orbitalsonic.chatgpt.helpers.datamodel.ChatCompletionRequest
import com.orbitalsonic.chatgpt.helpers.datamodel.ChatCompletionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit


import com.orbitalsonic.chatgpt.helpers.datamodel.ChatMessage
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class OpenApiClient {
    private val httpClient = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Authorization", "Bearer ${OpenAiInitializer.CHAT_GPT_API_KEY}")
                .header("Content-Type", "application/json")
                .build()

            chain.proceed(request)
        }

        writeTimeout(1, TimeUnit.MINUTES)
        readTimeout(1, TimeUnit.MINUTES)
        connectTimeout(1, TimeUnit.MINUTES)
    }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    private val apiService = retrofit.create(OpenAIChatService::class.java)

    fun getChatCompletion(
        messages: List<ChatMessage>,
        model: String = "gpt-3.5-turbo",
        completionHandler: (ChatCompletionResponse?, Throwable?) -> Unit
    ) {
        val requestBody = ChatCompletionRequest(model, messages,0.7,500)
        val call = apiService.getChatCompletion(requestBody)

        call.enqueue(object : Callback<ChatCompletionResponse> {
            override fun onResponse(
                call: Call<ChatCompletionResponse>,
                response: Response<ChatCompletionResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    completionHandler(result, null)
                } else {
                    val error = HttpException(response)
                    completionHandler(null, error)
                }
            }

            override fun onFailure(call: Call<ChatCompletionResponse>, t: Throwable) {
                completionHandler(null, t)
            }
        })
    }

}
