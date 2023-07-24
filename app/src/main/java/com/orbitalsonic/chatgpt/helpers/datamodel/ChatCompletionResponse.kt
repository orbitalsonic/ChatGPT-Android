package com.orbitalsonic.chatgpt.helpers.datamodel

import com.google.gson.annotations.SerializedName

data class ChatCompletionResponse(
    @SerializedName("id") val id: String,
    @SerializedName("object") val `object`: String,
    @SerializedName("created") val created: Int,
    @SerializedName("choices") val choices: List<Choice>,
    @SerializedName("usage") val usage: Usage,
)