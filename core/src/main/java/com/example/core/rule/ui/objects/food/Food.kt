package com.example.core.rule.ui.objects.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.core.rule.ui.objects.space.Space

@Entity(primaryKeys = ["x", "y"])
data class Food (
    var x: Int = 0,
    var y: Int = 0,
    var foodImage: Space.FoodImage = Space.FoodImage.FOOD_M,
    var satiety: Int = 0,
    @ColumnInfo(name = "space_id")
    val spaceId: Int = 0,
)
