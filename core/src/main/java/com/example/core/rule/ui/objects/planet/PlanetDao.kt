package com.example.core.rule.ui.objects.planet

import androidx.room.*

@Dao
interface PlanetDao {
    @Insert
    suspend fun insertPlanet(vararg planet: Planet)

    @Update
    suspend fun updatePlanet(vararg planet: Planet)

    @Delete
    suspend fun deletePlanet(vararg planet:Planet)

    @Query("SELECT * FROM planet ")
    fun getAllPlanet(): MutableList<Planet?>

}