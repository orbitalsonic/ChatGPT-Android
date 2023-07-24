package com.orbitalsonic.chatgpt.roomdb.repository

import androidx.annotation.WorkerThread
import com.orbitalsonic.chatgpt.roomdb.daos.AiChatDao
import com.orbitalsonic.chatgpt.roomdb.tables.ChatTable
import kotlinx.coroutines.flow.Flow

class AiChatRepository(private val aiChatDao: AiChatDao) {

    val allChatList: Flow<List<ChatTable>> = aiChatDao.getAllChatList()

    @WorkerThread
    suspend fun insertChat(chatTable: ChatTable) {
        aiChatDao.insertChat(chatTable)
    }

    @WorkerThread
    suspend fun deleteChat(chatTable: ChatTable) {
        aiChatDao.deleteChat(chatTable)
    }

    @WorkerThread
    suspend fun updateChat(chatTable: ChatTable) {
        aiChatDao.updateChat(chatTable)
    }

    @WorkerThread
    suspend fun deleteAllChat() {
        aiChatDao.deleteAllChat()
    }
}