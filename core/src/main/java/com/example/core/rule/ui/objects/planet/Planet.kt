package com.example.core.rule.ui.objects.planet

import android.graphics.drawable.Drawable
import androidx.room.Entity

@Entity(primaryKeys = ["x", "y"])
data class Planet(
    var x: Int,
    var y: Int,
    var planetImage: ConvertPlanetImage.CommandImage,
    var planetInfect: Int,
    var satiety: Int,
    var age: Int,
)
