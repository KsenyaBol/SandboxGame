package com.example.core.rule.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorldGameInfo (
    @PrimaryKey var nameWorld: String,
    var timeSave: String,
    var dateSave: String,
    @ColumnInfo(name = "space_id")
    val spaceId: Int,
)