package com.example.core.rule.ui.database

import androidx.room.Embedded
import androidx.room.Relation
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.space.Space


class SpaceWithPlanetAndFood {

    @Embedded
    var space: Space? = null

    @Relation(parentColumn = "id", entityColumn = "space_id", entity = Planet::class)
    var allPlanet: List<Planet?>? = null

    @Relation(parentColumn = "id", entityColumn = "space_id", entity = Food::class)
    var allFood: List<Food?>? = null

    @Relation(parentColumn = "id", entityColumn = "space_id", entity = WorldGameInfo::class)
    var allWorld: List<WorldGameInfo?>? = null



}