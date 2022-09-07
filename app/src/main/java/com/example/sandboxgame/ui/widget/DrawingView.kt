package com.example.sandboxgame.ui.widget


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class DrawingView: View {

    private val paint: Paint = Paint()

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)


    init {
        setWillNotDraw(false)
        paintSettings()
//        this.layoutParams.width = height
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(10F, 20F, 30F, 40F, paint)
        canvas.drawRect(450F, 0F, (height/1F)+450F, height/1F, paint)

    }

    private fun paintSettings() {
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        paint.strokeWidth = 2F
    }




}
