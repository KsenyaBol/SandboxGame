package com.example.core.rule.ui.objects.space

import androidx.room.*
import com.example.core.rule.ui.database.SpaceWithPlanetAndFood
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.planet.Planet


@Dao
interface SpaceDao {

    @Transaction
    suspend fun setSpace(id: SpaceObject, planet: List<Planet>, food: List<Food>) {
        deleteSpace(id)
        insertSpace(id, planet, food )
    }

    @Insert
    fun insertSpace(id: SpaceObject, planet: List<Planet>, food: List<Food>)

    @Update
    fun updateSpace(id: SpaceObject?)

    @Delete
    fun deleteSpace(id: SpaceObject)

    @Query("SELECT id from SpaceObject")
    fun getSpaceWithPlanetAndFood(): List<SpaceWithPlanetAndFood?>?

}