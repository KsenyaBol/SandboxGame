package com.example.core.rule.ui.database

import androidx.room.*


@Dao
interface WorldGameInfoDao {

    @Insert
    fun insertWorld(vararg worldGameInfo: WorldGameInfo?)

    @Update
    fun updateWorld(vararg worldGameInfo: WorldGameInfo?)

    @Delete
    fun deleteWorld(vararg worldGameInfo: WorldGameInfo)

    @Query("SELECT * FROM worldGameInfo")
    fun getAllWorld(): MutableList<WorldGameInfo?>

    @Query("SELECT * FROM worldGameInfo WHERE nameWorld LIKE :nameWorld")
    fun getAllWorldWithTime(vararg nameWorld: String?): List<WorldGameInfo?>?

    @Query(
        "SELECT planet.planetX, planet.planetY, planet.planetImage, planet.planetInfect, planet.planetSatiety, planet.age, worldGameInfo" +
                ".nameWorld, worldGameInfo.timeSave, worldGameInfo.dateSave, food.x, food.y, food.foodImage, food.satiety " +
                "AS space_id " +
                "FROM planet, worldGameInfo, food " +
                "WHERE planet.space_id == planet.space_id"
    )
    fun getSpaceWithPlanetAndFood(): List<SpacePlanetFoodNameOfWorld?>?
}