package com.example.core.rule.ui.objects.food

import androidx.room.*

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFood(vararg food: Food?)

    @Update
    suspend fun updateFood(vararg food: Food?)

    @Delete
    suspend fun deleteFood(vararg food: Food)

    @Query("SELECT * FROM food WHERE spaceId = :spaceId")
    fun getAllFood(spaceId: Int): List<Food>
//
//    @Query("SELECT * FROM food WHERE satiety LIKE :satiety")
//    fun getAllFoodWithSatiety(vararg satiety: Int?): List<Food?>?

}