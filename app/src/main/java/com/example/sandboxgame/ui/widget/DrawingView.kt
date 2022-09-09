package com.example.sandboxgame.ui.widget


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.sandboxgame.ui.size.SizePresenter

class DrawingView: View {

    private val paint: Paint = Paint()

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)


    init {
        setWillNotDraw(false)
        paintSettings()
    }

//    override fun drawingView() {
//
//    }




    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val size = height/50F
       for (j in 0 until 50)
           for (i in 0 until 50)
           {
               val x = size * j
               val y = size * i
               paint.color = Color.WHITE
               canvas.drawRect(x, y, size * (j + 1F), size * (i + 1F), paint)
           }

    }

    private fun paintSettings() {
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        paint.strokeWidth = 2F
    }




}
