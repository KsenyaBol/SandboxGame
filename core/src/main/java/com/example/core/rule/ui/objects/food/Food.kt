package com.example.core.rule.ui.objects.food

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["x", "y"])
data class Food (
    var x: Int,
    var y: Int,
    var foodImage: ConvertFoodImage.Food,
    var satiety: Int,
    @ColumnInfo(name = "space_id")
    val spaceId: Int,
)