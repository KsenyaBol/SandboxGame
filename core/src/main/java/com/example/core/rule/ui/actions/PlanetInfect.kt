package com.example.core.rule.ui.actions

import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.space.Space

class PlanetInfect {

    lateinit var space: Space

    init {
        planetInfect()
    }

    fun planetInfect() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            space.myPlanetList.forEachIndexed { index, planet ->
                val x = planet.x
                val y = planet.y
                var planetInfect = planet.planetInfect
                planetInfect += 2
                space.infectChange(index, x, y, planetInfect)
                space.planetDie(x, y, planetInfect)
            }
            handler.postDelayed(runnable!!, 2000)
        }
        handler.postDelayed(runnable, 2000)

    }
}