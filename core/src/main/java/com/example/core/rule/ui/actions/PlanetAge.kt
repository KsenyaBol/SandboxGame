package com.example.core.rule.ui.actions

import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.space.Space

class PlanetAge {

    var id: Int = 0
    var space: Space = Space(id)
    var pause_flg = true

    init {
        planetAge()
    }

    fun planetAge() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            if (pause_flg == true) {
                space.myPlanetList.forEachIndexed { index, planet ->
                val index = index
                val x = planet.planetX
                val y = planet.planetY
                var age = planet.age

                age += 2
                space.ageChange(index, x, y, age)
                space.planetDie(x, y, age)
            }
                handler.postDelayed(runnable!!, 3000)}
        }
        handler.postDelayed(runnable, 3000)

    }
}