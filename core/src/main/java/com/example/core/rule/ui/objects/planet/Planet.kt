package com.example.core.rule.ui.objects.planet

import android.graphics.drawable.Drawable

data class Planet(
    var x: Int,
    var y: Int,
    var planetImage: Drawable,
    var planetInfect: Boolean,
    var satiety: Int,
)
