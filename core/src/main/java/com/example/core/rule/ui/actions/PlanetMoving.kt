package com.example.core.rule.ui.actions

import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.space.Space

open class PlanetMoving {

    var pause_flg = true
    var size: Int = 0
    var millis: Int = 200
    lateinit var space: Space


    init {
        planetMoving()
    }

    private fun planetMoving() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            if (pause_flg == true) {
                space.myPlanetList.forEachIndexed { index, planet ->

                    var x = planet.x
                    var y = planet.y
                    val planetImage = planet.planetImage
                    val planetInfect = planet.planetInfect
                    val satiety = planet.satiety
                    val age = planet.age

                    when ((0..3).random()) {
                        0 -> {
                            if (x + 1 > size - 1) {
                                x -= 1
                            } else x += 1
                        }
                        1 -> {
                            if (y + 1 > size - 1) {
                                y -= 1
                            } else y += 1
                        }
                        2 -> {
                            if (x - 1 < 0) {
                                x += 1
                            } else x -= 1
                        }
                        3 -> {
                            if (y - 1 < 0) {
                                y += 1
                            } else y -= 1
                        }
                    }
                    space.planetMovingChange(index, x, y, planetImage, planetInfect, satiety, age)
                    space.planetSatiety(x, y, satiety, age)
                    space.planetDecay(x, y , planet.planetImage, planet.planetInfect, planet.satiety, age)
                }
            }
            handler.postDelayed(runnable!!, millis.toLong())
        }
        handler.postDelayed(runnable, millis.toLong())
    }


}