package com.example.core.rule.ui.actions

import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.space.Space

open class ParameterValues {

    var pause_flg = true
    var millis: Int = 200
    var space: Space? = null

    init {
        change()
        planetAge()
        foodAdd()
        planetInfect()
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
        val myPlanetList2 = ArrayList(space!!.myPlanetList)
        if (pause_flg == true) {
            myPlanetList2.forEach { planet ->
                var x = planet.planetX
                var y = planet.planetY
                val planetInfect = planet.planetInfect
                val satiety = planet.planetSatiety
                val age = planet.age

                when ((0..3).random()) {
                    0 -> {
                        if (x + 1 > space!!.size - 1) {
                            x -= 1
                        } else x += 1
                    }
                    1 -> {
                        if (y + 1 > space!!.size - 1) {
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
                space!!.planetMovingChange(x, y, planet.planetImage, planetInfect, satiety, age)
                space!!.planetSatiety(x, y, satiety, age)
                space!!.planetDecay(x, y , planet.planetImage, planet.planetInfect, planet.planetSatiety, age)
                space!!.planetDie(x, y, age)
            }
        }

    }

    fun reviewPlanet() {
        val myPlanetList2 = ArrayList(space!!.myPlanetList)
        if (pause_flg == true) {
            myPlanetList2.forEach { planet ->
                val xPlanet = planet.planetX
                val yPlanet = planet.planetY
                val food = space!!.myFoodList.firstOrNull { food ->
                    val foodX = food.x
                    val foodY = food.y
                    foodX == (xPlanet + 1) && foodY == yPlanet || foodX == (xPlanet - 1) && foodY == yPlanet
                            || foodY == (yPlanet + 1) && foodX == xPlanet || foodY == (yPlanet - 1) && foodX == xPlanet
                }
                if (food != null) {
                    space!!.reviewPlanet(xPlanet, yPlanet, food.x, food.y)
                    space!!.planetSatiety(food.x, food.y, planet.planetSatiety, planet.age)
                    space!!.planetDecay(food.x, food.y, planet.planetImage, planet.planetInfect, planet.planetSatiety, planet.age)
                    space!!.planetDie(food.x, food.x, planet.age)
                } else {
                    planetMoving()
                }
            }
        }
    }

    fun planetAge() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            if (pause_flg == true) {
                space!!.myPlanetList.forEachIndexed { index, planet ->
                    val x = planet.planetX
                    val y = planet.planetY
                    var age = planet.age

                    age += 2
                    space!!.ageChange(index, x, y, age)
                    space!!.planetDie(x, y, age)
                }
                handler.postDelayed(runnable!!, 3000)}
        }
        handler.postDelayed(runnable, 3000)

    }

    fun foodAdd() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            if (pause_flg == true) {
                space!!.foodChange((0..space!!.size).random(), (0..space!!.size).random(), 10)
            }
            handler.postDelayed(runnable!!, 1500)
        }
        handler.postDelayed(runnable, 1500)

    }

    fun planetInfect() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            if (pause_flg == true) {
                space!!.myPlanetList.forEachIndexed { index, planet ->
                    val x = planet.planetX
                    val y = planet.planetY
                    var planetInfect = planet.planetInfect
                    planetInfect += 2
                    space!!.infectChange(index, x, y, planetInfect)
                    space!!.planetDie(x, y, planetInfect)
                }
                handler.postDelayed(runnable!!, 2000)
            }
        }
        handler.postDelayed(runnable, 2000)
    }

}