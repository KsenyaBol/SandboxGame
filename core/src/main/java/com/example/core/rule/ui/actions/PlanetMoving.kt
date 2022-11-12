package com.example.core.rule.ui.actions

import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.space.Space

open class PlanetMoving {

    var pause_flg = true
    var size: Int = 0
    var millis: Int = 200
    var space: Space = Space()

    init {
        change()
    }

    private fun change() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            val random = (0..3).random()
            when(random) {
                0 -> planetMoving()
                1 -> reviewPlanet()
                2 -> reviewPlanet()
                3 -> reviewPlanet()
            }
            handler.postDelayed(runnable!!, millis.toLong())
        }
        handler.postDelayed(runnable, millis.toLong())
    }

    private fun planetMoving() {
        val myPlanetList2 = ArrayList(space.myPlanetList)
        if (pause_flg == true) {
            myPlanetList2.forEach { planet ->
                var x = planet.planetX
                var y = planet.planetY
                val planetInfect = planet.planetInfect
                val satiety = planet.planetSatiety
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
                space.planetMovingChange(x, y, planet.planetImage, planetInfect, satiety, age)
                space.planetSatiety(x, y, satiety, age)
                space.planetDecay(x, y , planet.planetImage, planet.planetInfect, planet.planetSatiety, age)
                space.planetDie(x, y, age)
            }
        }

    }

    fun reviewPlanet() {
        val myPlanetList2 = ArrayList(space.myPlanetList)
        if (pause_flg == true) {
            myPlanetList2.forEach { planet ->
                val xPlanet = planet.planetX
                val yPlanet = planet.planetY
                val food = space.myFoodList.firstOrNull { food ->
                    val foodX = food.x
                    val foodY = food.y
                    foodX == (xPlanet + 1) && foodY == yPlanet || foodX == (xPlanet - 1) && foodY == yPlanet
                            || foodY == (yPlanet + 1) && foodX == xPlanet || foodY == (yPlanet - 1) && foodX == xPlanet
                }
                if (food != null) {
                    space.reviewPlanet(xPlanet, yPlanet, food.x, food.y)
                    space.planetSatiety(food.x, food.y, planet.planetSatiety, planet.age)
                    space.planetDecay(food.x, food.y, planet.planetImage, planet.planetInfect, planet.planetSatiety, planet.age)
                    space.planetDie(food.x, food.x, planet.age)
                } else {
                    planetMoving()
                }
            }
        }
    }

}