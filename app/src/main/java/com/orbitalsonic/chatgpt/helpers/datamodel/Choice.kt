package com.orbitalsonic.chatgpt.helpers.datamodel

import com.google.gson.annotations.SerializedName

data class Choice(
    @SerializedName("index") val index: Int,
    @SerializedName("finish_reason") val finish_reason: String,
    @SerializedName("message") val message: ChatMessage)

