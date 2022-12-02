package com.example.engine

import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.space.Space

class ReviewPlanet {

    private var space: Space = Space()

//    fun mineOrStranger() {
//        val myPlanetList2 = ArrayList(space.myPlanetList)
//        myPlanetList2.forEachIndexed { index, planet->
//            var planetX = planet.planetX
//            var planetY = planet.planetY
//            if ()
//        }
//    }

//    init{
//        reviewPlanet()
//    }
//
//    fun reviewPlanet() {
//        val handler = Handler(Looper.getMainLooper())
//        var runnable: Runnable? = null
//
//        runnable = Runnable {
//            space.myPlanetList.forEach { planet ->
//                val xPlanet = planet.x
//                val yPlanet = planet.y
//
//                val food = space.myFoodList.firstOrNull { food ->
//                    food.x == (xPlanet + 1) || food.x == (xPlanet - 1) || food.y == (yPlanet + 1) || food.y == (yPlanet - 1)
//                }
//                if (food != null) {
//                    space.reviewPlanet(xPlanet, yPlanet, food.x, food.y)
//                }
//            }
//            handler.postDelayed(runnable!!, 100)
//        }
//        handler.postDelayed(runnable, 100)
//    }


}