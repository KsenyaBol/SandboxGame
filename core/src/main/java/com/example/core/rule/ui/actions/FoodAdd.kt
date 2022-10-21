package com.example.core.rule.ui.actions

import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import com.example.core.rule.ui.objects.space.Space

class FoodAdd {

    var size: Int = 10
    lateinit var space: Space
    lateinit var foodXS: Drawable
    lateinit var foodS: Drawable
    lateinit var foodM: Drawable
    lateinit var foodL: Drawable
    lateinit var imageFoodFinal: Drawable

    init {
        foodAdd()
    }

    fun foodAdd() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            space.foodChange((0..size).random(), (0..size).random(), foodM, 10)
            handler.postDelayed(runnable!!, 1500)
        }
        handler.postDelayed(runnable, 1500)

    }

}