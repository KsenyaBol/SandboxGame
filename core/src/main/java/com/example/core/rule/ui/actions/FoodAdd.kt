package com.example.core.rule.ui.actions

import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.space.Space

class FoodAdd {

    var size: Int = 10
    var id: Int = 0
    var pause_flg: Boolean = true
    var space: Space = Space(id)

    init {
        foodAdd()
    }

    fun foodAdd() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            if (pause_flg == true) {
                space.foodChange((0..size).random(), (0..size).random(), 10)
            }
            handler.postDelayed(runnable!!, 1500)
        }
        handler.postDelayed(runnable, 1500)

    }

}