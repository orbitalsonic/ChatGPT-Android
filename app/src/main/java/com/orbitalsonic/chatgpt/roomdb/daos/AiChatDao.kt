package com.orbitalsonic.chatgpt.roomdb.daos

import androidx.room.*
import com.orbitalsonic.chatgpt.roomdb.tables.ChatTable
import kotlinx.coroutines.flow.Flow

@Dao
interface AiChatDao {
    @Query("SELECT * FROM chat_table ORDER BY id ASC")
    fun getAllChatList(): Flow<List<ChatTable>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChat(chatTable: ChatTable)

    @Delete
    suspend fun deleteChat(chatTable: ChatTable)

    @Update
    suspend fun updateChat(chatTable: ChatTable)

    @Query("DELETE FROM chat_table")
    suspend fun deleteAllChat()
}