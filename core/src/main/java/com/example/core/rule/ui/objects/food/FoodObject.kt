package com.example.core.rule.ui.objects.food

import com.example.core.rule.ui.objects.space.Space

data class FoodObject (
    override var x: Int,
    override var y: Int,
    override var foodImage: Space.FoodImage,
    override var satiety: Int
): Food