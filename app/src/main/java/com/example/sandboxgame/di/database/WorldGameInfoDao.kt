package com.example.sandboxgame.di.database

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
}