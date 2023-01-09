package com.example.core.rule.ui.actions

import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.space.Space

class PlanetInfect {

    var id: Int = 0
    var space: Space = Space(id)
    var pause_flg = true

    init {
        planetInfect()
    }

    fun planetInfect() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            if (pause_flg == true) {
                space.myPlanetList.forEachIndexed { index, planet ->
                    val x = planet.planetX
                    val y = planet.planetY
                    var planetInfect = planet.planetInfect
                    planetInfect += 2
                    space.infectChange(index, x, y, planetInfect)
                    space.planetDie(x, y, planetInfect)
                }
                handler.postDelayed(runnable!!, 2000)
            }
        }
        handler.postDelayed(runnable, 2000)
    }
}