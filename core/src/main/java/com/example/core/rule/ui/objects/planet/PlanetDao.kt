package com.example.core.rule.ui.objects.planet

import androidx.room.*
import com.example.core.rule.ui.database.SpacePlanetFoodNameOfWorld

@Dao
interface PlanetDao {
    @Insert
    fun insertPlanet(vararg planet: Planet?)

    @Update
    fun updatePlanet(vararg planet: Planet?)

    @Delete
    fun deletePlanet(vararg planet: Planet)

    @Query("SELECT * FROM planet")
    fun getAllPlanet(): MutableList<Planet?>

    @Query("SELECT * FROM planet WHERE age LIKE :age")
    fun getAllPlanetWithAge(vararg age: Int?): List<Planet?>?

//    @Query(
//        "SELECT planet.x, planet.y, planet.planetImage, planet.planetInfect, planet.satiety, planet.age AS space_id " +
//                "FROM planet, space " +
//                "WHERE space.id == planet.space_id"
//    )
//    fun getSpaceWithPlanetAndFood(): List<SpacePlanetFoodNameOfWorld?>?
}