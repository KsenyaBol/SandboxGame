package com.example.sandboxgame.di

import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.planet.Planet

data class WorldGameInfo (
    val nameWorld: String,
    val timeSave: String,
    val dateSave: String,
    val planet: ArrayList<Planet>,
    val food: ArrayList<Food>,
)