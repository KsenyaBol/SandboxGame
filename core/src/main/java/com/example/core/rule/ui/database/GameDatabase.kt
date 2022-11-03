package com.example.core.rule.ui.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.food.FoodDao
import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.planet.PlanetDao
import com.example.core.rule.ui.objects.space.Space
import com.example.core.rule.ui.objects.space.SpaceDao
import com.example.core.rule.ui.objects.space.SpaceListener
import com.example.core.rule.ui.objects.space.SpaceObject


@Database(entities = [SpaceObject::class, Planet::class, Food::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract val space: SpaceDao?
    abstract val planetDao: PlanetDao?
    abstract val foodDao: FoodDao?



}