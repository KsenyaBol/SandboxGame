package com.example.core.rule.ui.objects.planet

import androidx.room.*
import com.example.core.rule.ui.database.SpacePlanetFoodNameOfWorld

@Dao
interface PlanetDao {
    @Insert
    suspend fun insertPlanet(vararg planet: Planet?)

    @Update
    suspend fun updatePlanet(vararg planet: Planet?)

    @Delete
    suspend fun deletePlanet(vararg planet: Planet)

    @Query("SELECT * FROM planet")
    suspend fun getAllPlanet(): MutableList<Planet?>

    @Query("SELECT * FROM planet WHERE age LIKE :age")
    suspend fun getAllPlanetWithAge(vararg age: Int?): List<Planet?>?

//    @Query(
//        "SELECT planet.x, planet.y, planet.planetImage, planet.planetInfect, planet.satiety, planet.age AS space_id " +
//                "FROM planet, space " +
//                "WHERE space.id == planet.space_id"
//    )
//    fun getSpaceWithPlanetAndFood(): List<SpacePlanetFoodNameOfWorld?>?
}