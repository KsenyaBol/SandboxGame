package com.example.core.rule.ui.actions

import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.space.Space

class PlanetAge {

    var space: Space = Space()

    init {
        planetAge()
    }

    fun planetAge() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            space.myPlanetList.forEachIndexed { index, planet ->
               val index = index
               val x = planet.planetX
               val y = planet.planetY
               var age = planet.age

                age += 2
                space.ageChange(index, x, y, age)
                space.planetDie(x, y, age)
            }
            handler.postDelayed(runnable!!, 3000)
        }
        handler.postDelayed(runnable, 3000)

    }
}