package com.example.sandboxgame.di

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.example.core.rule.ui.database.GameDatabase


class App : Application() {

    companion object {
        var instance: App? = null
        var database: GameDatabase? = null

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = databaseBuilder(this, GameDatabase::class.java, "gamedatabase")
            .build()
    }

    fun getDatabase(): GameDatabase? {
        return database
    }


}