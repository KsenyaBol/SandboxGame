package com.example.core.rule.ui.objects.planet

import androidx.room.*

@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlanet(vararg planet: Planet)

    @Update
    suspend fun updatePlanet(vararg planet: Planet)

    @Delete
    suspend fun deletePlanet(vararg planet:Planet)

    @Query("SELECT * FROM planet WHERE spaceId = :spaceId")
    fun getAllPlanet(spaceId: Int): List<Planet>

}