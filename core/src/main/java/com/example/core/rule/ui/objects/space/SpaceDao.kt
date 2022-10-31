package com.example.core.rule.ui.objects.space

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.core.rule.ui.database.SpacePlanetFoodNameOfWorld
import com.example.core.rule.ui.database.SpaceWithPlanetAndFood
import com.example.core.rule.ui.database.WorldGameInfo

interface SpaceDao {

    @Insert
    fun insertSpace(vararg space: Space?)

    @Update
    fun updateSpace(vararg space: Space?)

    @Delete
    fun deleteSpace(vararg space: Space)

//    @Query ("SELECT id from space")
//    fun getSpaceWithPlanetAndFood(): List<SpaceWithPlanetAndFood?>?

}