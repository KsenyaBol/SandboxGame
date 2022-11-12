package com.example.core.rule.ui.objects.planet

import androidx.room.*

@Dao
interface PlanetDao {
    @Insert
    fun insertPlanet(vararg planet: Planet?)

    @Update
    fun updatePlanet(vararg planet: Planet?)

    @Delete
    fun deletePlanet(vararg planet: Planet)

//    @Query("SELECT * FROM planet")
//    fun getAllPlanet(): MutableList<Planet?>
//
//    @Query("SELECT * FROM planet WHERE age LIKE :age")
//    fun getAllPlanetWithAge(vararg age: Int?): List<Planet?>?

}