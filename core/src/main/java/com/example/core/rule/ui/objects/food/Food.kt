package com.example.core.rule.ui.objects.food

import androidx.room.Ignore
import com.example.core.rule.ui.objects.space.Space
import java.io.Serializable

interface Food: Serializable {

    var x: Int
    var y: Int
    var foodImage: Space.FoodImage
    var satiety: Int

}
