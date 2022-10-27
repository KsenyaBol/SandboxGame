package com.example.core.rule.ui.objects.food

import com.example.core.R
import com.example.core.rule.ui.objects.planet.ConvertPlanetImage

class ConvertFoodImage {
    var foodIm = R.drawable.planet_food_1
    var foodString = CommandFood.FOOD_XS

    fun foodImage(foodImage: CommandFood) {
        foodString = foodImage

        if (foodImage == CommandFood.FOOD_XS) {
            foodIm = R.drawable.planet_food_3
        }
        if (foodImage == CommandFood.FOOD_S) {
            foodIm = R.drawable.planet_food_2
        }
        if (foodImage == CommandFood.FOOD_M) {
            foodIm = R.drawable.planet_food_1
        }
        if (foodImage == CommandFood.FOOD_L) {
            foodIm = R.drawable.planet_food_4
        }

    }

    enum class CommandFood {
        FOOD_XS, FOOD_S, FOOD_M, FOOD_L
    }
}