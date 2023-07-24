package com.orbitalsonic.chatgpt.roomdb.tables

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity(tableName = "chat_table")
data class ChatTable(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "role_check") var roleCheck: Boolean,
    @ColumnInfo(name = "role") var role: String,
    @ColumnInfo(name = "content") var content: String
) : Parcelable