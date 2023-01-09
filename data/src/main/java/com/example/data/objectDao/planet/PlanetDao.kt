package com.example.data.objectDao.planet

import androidx.room.*

@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlanet(vararg planet: PlanetEntity)

    @Update
    suspend fun updatePlanet(vararg planet: PlanetEntity)

    @Delete
    suspend fun deletePlanet(vararg planet: PlanetEntity)

    @Query("SELECT * FROM planets WHERE space_id = :spaceId")
    fun getAllPlanet(spaceId: Int): List<PlanetEntity>

}