package com.example.core.rule.ui.objects.food

import android.graphics.drawable.Drawable
import androidx.room.Entity

@Entity(primaryKeys = ["x", "y"])
data class Food (
    var x: Int,
    var y: Int,
    var foodImage: ConvertFoodImage.CommandFood,
    var satiety: Int,
)