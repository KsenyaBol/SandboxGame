package com.example.sandboxgame.di.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorldGameInfo (
    @PrimaryKey
    @ColumnInfo(name = "nameWorld")
    val nameWorld: String,
    val timeSave: String,
    val dateSave: String,
)