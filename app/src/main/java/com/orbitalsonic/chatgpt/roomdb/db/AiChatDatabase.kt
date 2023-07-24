package com.orbitalsonic.chatgpt.roomdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.orbitalsonic.chatgpt.roomdb.daos.AiChatDao
import com.orbitalsonic.chatgpt.roomdb.tables.ChatTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [ChatTable::class],
    version = 1,
    exportSchema = false
)
abstract class AiChatDatabase : RoomDatabase() {

    abstract fun aiChatDao(): AiChatDao

    companion object {

        @Volatile
        private var INSTANCE: AiChatDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): AiChatDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AiChatDatabase::class.java,
                    "ai_chat_databases"
                ).addCallback(AiChatDBCallback(scope, context))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AiChatDBCallback(private val scope: CoroutineScope, context: Context) :
        Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    // By-Default items on database creation
                }
            }
        }
    }
}