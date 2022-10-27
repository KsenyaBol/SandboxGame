package com.example.sandboxgame.di

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.example.sandboxgame.di.database.GameDatabase


class App : Application() {
    private var database: GameDatabase? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        database = databaseBuilder(this, GameDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    fun getDatabase(): GameDatabase? {
        return database
    }

    companion object {
        var instance: App? = null
    }
}