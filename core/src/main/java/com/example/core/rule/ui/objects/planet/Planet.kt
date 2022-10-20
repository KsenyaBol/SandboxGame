package com.example.core.rule.ui.objects.planet

import android.graphics.drawable.Drawable

data class Planet(
    var x: Int,
    var y: Int,
    var planetImage: Drawable,
    var planetInfect: Int,
    var satiety: Int,
    var age: Int,
)
