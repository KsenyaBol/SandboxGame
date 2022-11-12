package com.example.core.rule.ui.objects.food

import androidx.room.*

@Dao
interface FoodDao {

    @Insert
    fun insertFood(vararg food: Food?)

    @Update
    fun updateFood(vararg food: Food?)

    @Delete
    fun deleteFood(vararg food: Food)

//    @Query("SELECT * FROM food")
//    fun getAllFood(): MutableList<Food?>
//
//    @Query("SELECT * FROM food WHERE satiety LIKE :satiety")
//    fun getAllFoodWithSatiety(vararg satiety: Int?): List<Food?>?

}