package com.example.core.rule.ui.objects.space

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

data class SpaceObject (

    var id: Int = 0,
    var size: Int = 10,

): java.io.Serializable