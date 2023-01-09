package com.example.data.objectDao.food

import androidx.room.*

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFood(vararg food: FoodEntity?)

    @Update
    suspend fun updateFood(vararg food: FoodEntity?)

    @Delete
    suspend fun deleteFood(vararg food: FoodEntity)

    @Query("SELECT * FROM foods WHERE space_id = :spaceId")
    fun getAllFood(spaceId: Int): List<FoodEntity>

}