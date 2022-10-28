package com.example.core.rule.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorldGameInfo (
    @PrimaryKey
    @ColumnInfo(name = "nameWorld")
    var nameWorld: String,
    var timeSave: String,
    var dateSave: String,
)