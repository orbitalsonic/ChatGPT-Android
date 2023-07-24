package com.orbitalsonic.chatgpt.helpers.datamodel
import com.google.gson.annotations.SerializedName

data class ChatCompletionRequest(
    @SerializedName("model") val model: String,
    @SerializedName("messages") val messages: List<ChatMessage>,
    @SerializedName("temperature") val temperature: Double,
    @SerializedName("max_tokens") val max_tokens: Int
)