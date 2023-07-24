package com.orbitalsonic.chatgpt.helpers.interfaces

import com.orbitalsonic.chatgpt.roomdb.tables.ChatTable

interface OnChatItemClickListener {
    fun onItemCopy(chatTable: ChatTable)
}