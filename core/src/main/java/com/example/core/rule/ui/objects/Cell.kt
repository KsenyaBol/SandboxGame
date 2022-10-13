package com.example.core.rule.ui.objects

import android.graphics.drawable.Drawable

data class Cell(
    var x: Int,
    var y: Int,
    var cellImage: Drawable,
    var cellInfect: Boolean,
)
