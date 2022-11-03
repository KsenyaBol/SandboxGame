package com.example.core.rule.ui.objects.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.core.rule.ui.objects.space.Space

@Entity(primaryKeys = ["x", "y"])
data class Food (
    var x: Int,
    var y: Int,
    var foodImage: Space.FoodImage,
    var satiety: Int,
    @ColumnInfo(name = "space_id")
    val spaceId: Int,
)