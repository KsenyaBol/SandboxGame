package com.example.core.rule.ui.move

import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.space.Space

open class PlanetMoving {

    var pause_flg = true
    var size: Int = 0
    var millis: Int = 200
//    var space: Space = Space()


//    init {
//        planetMoving()
//    }
//
//    private fun planetMoving() {
//        val handler = Handler(Looper.getMainLooper())
//        var runnable: Runnable? = null
//
//        runnable = Runnable {
//            if (pause_flg == true) {
//                space.myPlanetList.forEachIndexed { index, planet ->
//
//                    var x = planet.x
//                    var y = planet.y
//                    val index = index
//                    val rand = (0..3).random()
//
//                    when (rand) {
//                        0 -> {
//                            if (x + 1 > size - 1) {
//                                x -= 1
//                            } else x += 1
//                        }
//                        1 -> {
//                            if (y + 1 > size - 1) {
//                                y -= 1
//                            } else y += 1
//                        }
//                        2 -> {
//                            if (x - 1 < 0) {
//                                x += 1
//                            } else x -= 1
//                        }
//                        3 -> {
//                            if (y - 1 < 0) {
//                                y += 1
//                            } else y -= 1
//                        }
//                    }
//                    space.planetMovingChange(index, x, y, planet.planetImage, planet.planetInfect)
//                }
//                space.change()
//            }
//            handler.postDelayed(runnable!!, millis.toLong())
//        }
//        handler.postDelayed(runnable, millis.toLong())
//    }

}