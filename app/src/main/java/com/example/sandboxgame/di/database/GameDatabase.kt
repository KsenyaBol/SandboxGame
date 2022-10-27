package com.example.sandboxgame.di.database

import android.content.ContentValues
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.food.FoodDao
import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.planet.PlanetDao


@Database(entities = [WorldGameInfo::class, Planet::class, Food::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract val worldGameInfoDao: WorldGameInfoDao?
    abstract val planetDao: PlanetDao?
    abstract val foodDao: FoodDao?

//    open fun insertPicture(data: ByteArray?) {
//        val values = ContentValues()
//        values.put("photo", data)
//        mDatabase?.insert("Таблица", null, values)
//        close()
//    }
}