package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.rule.ui.objects.space.Space
import com.example.data.objectDao.space.SpaceEntity
import com.example.data.objectDao.food.FoodDao
import com.example.data.objectDao.food.FoodEntity
import com.example.data.objectDao.planet.PlanetDao
import com.example.data.objectDao.planet.PlanetEntity
import com.example.data.objectDao.space.SpaceDao

@Database(entities = [SpaceEntity::class, PlanetEntity::class, FoodEntity::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract val spaceDao: SpaceDao
    abstract val planetDao: PlanetDao
    abstract val foodDao: FoodDao

//suspend fun saveSpace(space: SpaceEntity): Int {
//        return spaceDao.insertSpace(space)
//
//}

}