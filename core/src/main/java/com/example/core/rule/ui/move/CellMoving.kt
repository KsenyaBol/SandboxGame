package com.example.core.rule.ui.move

import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.Cell
import com.example.core.rule.ui.objects.Space

class CellMoving {

    init {
        cellMoving()
    }

    fun cellMoving() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null
        runnable = Runnable {
            Space().myCellList.forEachIndexed { index, cell ->

                var x = cell.x
                var y = cell.y
                val rand = (0..3).random()

                when (rand) {
                    0 -> {
                        if (x + 1 > Space().size - 1) {
                            x -= 1
                        } else x += 1
                    }
                    1 -> {
                        if (y + 1 > Space().size - 1) {
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


                Space().myCellList[index] = Cell(x = x, y = y, cellColor = cell.cellColor, cellInfect = cell.cellInfect)
            }
            handler.postDelayed(runnable!!, 200)

        }
        handler.postDelayed(runnable, 200)

    }

}