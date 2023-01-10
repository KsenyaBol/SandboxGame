package com.example.data.database

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.objectDao.food.FoodEntity
import com.example.data.objectDao.planet.PlanetEntity
import com.example.data.objectDao.space.SpaceEntity

data class SpaceToManyRelationship(
    @Embedded
    var spaceEntity: SpaceEntity,

    @Relation(parentColumn = "id", entityColumn = "space_id", entity = PlanetEntity::class)
    var planet: List<PlanetEntity>,

    @Relation(parentColumn = "id", entityColumn = "space_id", entity = FoodEntity::class)
    var food: List<FoodEntity>,
)



