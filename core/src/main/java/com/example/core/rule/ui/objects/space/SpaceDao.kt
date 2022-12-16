package com.example.core.rule.ui.objects.space

import androidx.room.*
import com.example.core.rule.ui.database.SpaceWithPlanetAndFood
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.planet.Planet

@Dao
interface SpaceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSpace(id: SpaceObject, planet: List<Planet>, food: List<Food>)

    @Update
     suspend fun updateSpace(id: SpaceObject, planet: List<Planet>, food: List<Food>)

    @Delete
    suspend fun deleteSpace(id: SpaceObject, planet: List<Planet>, food: List<Food>)

//    @Query("SELECT * FROM space")
//    suspend fun getSpaceWithPlanetAndFood(): List<SpaceWithPlanetAndFood?>?

    @Query("SELECT * FROM space WHERE id = :id")
    suspend fun getSpace(id: Int): SpaceWithPlanetAndFood

//    @Query("SELECT * FROM space WHERE id = :id")
//    suspend fun findById(id: Int) : SpaceObject

}