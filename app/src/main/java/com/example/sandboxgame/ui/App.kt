package com.example.sandboxgame.ui

import android.app.Application
import androidx.annotation.Nullable
import androidx.room.Room.databaseBuilder
import com.example.core.rule.ui.database.GameDatabase
import com.example.core.rule.ui.objects.space.Space


class App : Application() {

    companion object {
        var instance: App? = null
        lateinit var database: GameDatabase
//        @Nullable
//        lateinit var space: Space

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = databaseBuilder(this, GameDatabase::class.java, "gamedatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    fun getDatabase(): GameDatabase {
        return database
    }


}