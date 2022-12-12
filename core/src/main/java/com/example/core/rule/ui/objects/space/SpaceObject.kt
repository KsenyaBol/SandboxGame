package com.example.core.rule.ui.objects.space

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity (
    tableName = "space"
        )
data class SpaceObject (

    @PrimaryKey
    var id: Int = 0

): java.io.Serializable