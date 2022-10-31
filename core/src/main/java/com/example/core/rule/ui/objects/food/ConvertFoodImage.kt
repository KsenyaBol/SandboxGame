package com.example.core.rule.ui.objects.food

class ConvertFoodImage {
    var food_M = Food.FOOD_M

    fun foodImage(foodImage: Food) {}

    enum class Food {
        FOOD_XS, FOOD_S, FOOD_M, FOOD_L
    }
}