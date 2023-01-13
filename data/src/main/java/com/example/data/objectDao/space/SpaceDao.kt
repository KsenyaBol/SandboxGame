package com.example.data.objectDao.space

import androidx.room.*
import com.example.data.database.SpaceToManyRelationship

@Dao
interface SpaceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSpace(spaceEntity: SpaceEntity): Long

    @Update
    suspend fun updateSpace(spaceEntity: SpaceEntity)

    @Delete
    suspend fun deleteSpace(spaceEntity: SpaceEntity)

    @Query("SELECT * FROM space WHERE id = :id")
    suspend fun getSpace(id: Int): SpaceToManyRelationship

    @Query("SELECT * FROM space")
    suspend fun getAllSpace(): List<SpaceEntity>

    @Query("SELECT COUNT(*) FROM space")
    suspend fun getAllSpaceSize(): Int


}