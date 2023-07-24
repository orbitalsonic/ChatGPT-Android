package com.orbitalsonic.chatgpt.roomdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.orbitalsonic.chatgpt.roomdb.repository.AiChatRepository
import com.orbitalsonic.chatgpt.roomdb.tables.ChatTable
import kotlinx.coroutines.launch

class AiChatViewModel(private val repository: AiChatRepository) : ViewModel() {

    val allChatList: LiveData<List<ChatTable>> = repository.allChatList.asLiveData()

    fun insertChat(chatTable: ChatTable) = viewModelScope.launch {
        repository.insertChat(chatTable)
    }

    fun deleteChat(chatTable: ChatTable) = viewModelScope.launch {
        repository.deleteChat(chatTable)
    }

    fun updateChat(chatTable: ChatTable) = viewModelScope.launch {
        repository.updateChat(chatTable)
    }

    fun deleteAllChat() = viewModelScope.launch {
        repository.deleteAllChat()
    }

}