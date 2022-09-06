package com.example.sandboxgame.ui.widget


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable


abstract class DrawingView: View {

    private val paint: Paint = Paint()
    private fun PaintSettings() {
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        paint.strokeWidth = 10F
    }

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

}
