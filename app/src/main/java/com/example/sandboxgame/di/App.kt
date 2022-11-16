package com.example.sandboxgame.di

import android.annotation.SuppressLint
import android.app.Application
import android.os.StrictMode
import androidx.room.Room.databaseBuilder
import com.example.core.rule.ui.database.GameDatabase
import com.example.sandboxgame.BuildConfig


class App : Application() {

    companion object {
        var instance: App? = null
        lateinit var database: GameDatabase

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