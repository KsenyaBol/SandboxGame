package com.example.core.rule.ui.objects.planet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.rule.ui.objects.space.Space
import kotlinx.serialization.Serializable

@Entity (primaryKeys = ["planetX", "planetY"])
data class Planet (
    var planetX: Int = 0,
    var planetY: Int = 0,
    var planetImage: Space.PlanetImage = Space.PlanetImage.PLANET1,
    var planetInfect: Int = 0,
    var planetSatiety: Int = 0,
    var age: Int = 0,
    @ColumnInfo(name = "space_id")
    val spaceId: Int = 0,
): java.io.Serializable
