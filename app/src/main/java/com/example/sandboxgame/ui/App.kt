package com.example.sandboxgame.ui

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.example.core.rule.ui.database.GameDatabase


class App : Application() {

    companion object {
        var instance: App? = null
        lateinit var database: GameDatabase

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = databaseBuilder(this, GameDatabase::class.java, "gamedatabase")
            .allowMainThreadQueries()
            .build()
    }

    fun getDatabase(): GameDatabase {
        return database
    }


}