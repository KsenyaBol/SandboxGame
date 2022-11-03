package com.example.core.rule.ui.objects.planet

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.core.rule.ui.objects.space.Space

@Entity(primaryKeys = ["planetX", "planetY"])
data class Planet(
    var planetX: Int,
    var planetY: Int,
    var planetImage: Space.PlanetImage,
    var planetInfect: Int,
    var planetSatiety: Int,
    var age: Int,
    @ColumnInfo(name = "space_id")
    val spaceId: Int,
)
