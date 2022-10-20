package com.example.core.rule.ui.actions

import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.space.Space

class PlanetAge {

    lateinit var space: Space

    init {
        planetAge()
    }

    fun planetAge() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            space.myPlanetList.forEachIndexed { index, planet ->
               val index = index
               val x = planet.x
               val y = planet.y
               var age = planet.age

                age += 2
                space.ageChange(index, x, y, age)
                space.planetDie()
            }
            handler.postDelayed(runnable!!, 2000)
        }
        handler.postDelayed(runnable, 2000)

    }
}