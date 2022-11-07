package com.example.core.rule.ui.database

import android.content.Context
import androidx.room.Room
import com.example.core.rule.ui.objects.space.Space

object DataBaseBuilder {
    private var INSTANCE: GameDatabase? = null
    fun getInstance(context: Context, space: Space): GameDatabase {
        if (INSTANCE == null) {
            synchronized(GameDatabase::class) {
                INSTANCE = buildRoomDB(context, space)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context, space: Space) =
        Room.databaseBuilder(
            context.applicationContext,
            GameDatabase::class.java,
            "database-builder"
        ).build()

}