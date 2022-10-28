package com.example.core.rule.ui.objects.food

import com.example.core.R
import com.example.core.rule.ui.objects.planet.ConvertPlanetImage

class ConvertFoodImage {
    var foodString = CommandFood.FOOD_XS
    var food_M = CommandFood.FOOD_M

    fun foodImage(foodImage: CommandFood) {
        foodString = foodImage

    }

    enum class CommandFood {
        FOOD_XS, FOOD_S, FOOD_M, FOOD_L
    }
}