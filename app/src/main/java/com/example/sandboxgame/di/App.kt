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
        var database: GameDatabase? = null

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = databaseBuilder(this, GameDatabase::class.java, "gamedatabase")
            .build()
    }

    @SuppressLint("NotConstructor")
    fun App() {
        if (BuildConfig.DEBUG) StrictMode.enableDefaults()
    }

    fun getDatabase(): GameDatabase? {
        return database
    }


}