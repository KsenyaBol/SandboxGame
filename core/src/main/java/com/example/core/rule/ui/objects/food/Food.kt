package com.example.core.rule.ui.objects.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.space.Space
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity
data class Food (
    var x: Int = 0,
    var y: Int = 0,
    var foodImage: Space.FoodImage = Space.FoodImage.FOOD_M,
    var satiety: Int = 0,
    @PrimaryKey @ColumnInfo(name = "spaceId")
    val spaceId: Int = 0,
): java.io.Serializable
