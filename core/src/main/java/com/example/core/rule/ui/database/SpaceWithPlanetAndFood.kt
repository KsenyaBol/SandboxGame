package com.example.core.rule.ui.database

import androidx.room.Embedded
import androidx.room.Relation
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.space.Space
import com.example.core.rule.ui.objects.space.SpaceObject

class SpaceWithPlanetAndFood {

    @Embedded
    var space: SpaceObject? = null

    @Relation(parentColumn = "id", entityColumn = "space_id", entity = Planet::class)
    var planet: ArrayList<Planet?>? = null

    @Relation(parentColumn = "id", entityColumn = "space_id", entity = Food::class)
    var food: ArrayList<Food?>? = null

}