package com.example.data.objectDao.space

import android.util.Size
import androidx.room.*
import com.example.core.rule.ui.objects.space.Space
import com.example.core.rule.ui.objects.space.SpaceObject
import com.example.data.database.SpaceToManyRelationship
import com.example.data.objectDao.food.FoodEntity
import com.example.data.objectDao.planet.PlanetEntity

@Dao
interface SpaceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSpace(spaceEntity: SpaceEntity): Int

    @Update
    suspend fun updateSpace(spaceEntity: SpaceEntity, planet: List<PlanetEntity>, food: List<FoodEntity>)

    @Delete
    suspend fun deleteSpace(spaceEntity: SpaceEntity, planet: List<PlanetEntity>?, food: List<FoodEntity>?)

    @Query("SELECT * FROM space WHERE id = :id")
    suspend fun getSpace(id: Int): SpaceToManyRelationship

    @Query("SELECT * FROM space")
    suspend fun getAllSpace(): List<SpaceEntity>

//    @Query("SELECT * FROM space WHERE id = :id")
//    suspend fun getSize(id: Int):

    @Query("SELECT COUNT(*) FROM space")
    suspend fun getAllSpaceSize(): Int


}