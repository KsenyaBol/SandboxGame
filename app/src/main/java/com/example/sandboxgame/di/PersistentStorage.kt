package com.example.sandboxgame.di

import android.content.Context
import android.content.SharedPreferences

class PersistentStorage {

    companion object {

        private const val APP_PREFERENCES = "Settungs"
        private const val APP_PREFERENCES_NAME = "WorldName"
        private const val APP_PREFERENCES_TIME = "TimeOfSaved"
        private const val APP_PREFERENCES_DATE = "DateOfSaved"
        private const val APP_PREFERENCES_PLANET = "Planet"
        private const val APP_PREFERENCES_FOOD = "Food"

    }
    private var settings: SharedPreferences? = null
    private var editor: SharedPreferences.Editor?  = null
    private var context: Context?  = null

    private fun init(cntxt: Context) {
        context = cntxt
    }

    private fun init() {
        settings = context?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        editor = settings?.edit()
//        var worldName = context?.getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE)

    }

    fun addProperty(nameWorld: String) {
        if(settings == null) {
            init()
        }
        editor?.putString(APP_PREFERENCES_NAME, nameWorld)
        editor?.apply()
    }

    fun getProperty(nameWorld: String): String? {
        if(settings == null) {
            init()
        }
        return settings?.getString(APP_PREFERENCES_NAME, nameWorld )
    }

}
