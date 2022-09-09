package com.example.sandboxgame.ui.widget


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class DrawingView: View{

    var size: Int = 0

    var onTapCellListener: OnTapCellListener? = null

    private val paint: Paint = Paint()

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init {
        setWillNotDraw(false)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val sizeH = height/size.toFloat()

                val i = (event.x / sizeH).toInt()
                val j = (event.y / sizeH).toInt()

                onTapCellListener?.onTapCell(i, j)
            }
        }
        return super.onTouchEvent(event)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val sizeH = height/size.toFloat()

       for (j in 0 until size)
           for (i in 0 until size)
           {
               val x = sizeH * i
               val y = sizeH * j
               paint.color = Color.WHITE
               canvas.drawRect(x, y, x + sizeH , y + sizeH, paint)
           }


    }

    interface OnTapCellListener {

        fun onTapCell(i: Int, j: Int)

    }

}

//rgb((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt())