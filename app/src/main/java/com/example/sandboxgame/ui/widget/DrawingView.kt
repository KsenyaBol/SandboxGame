package com.example.sandboxgame.ui.widget


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlinx.parcelize.Parcelize


@Parcelize
class MyState(private val superSaveState: Parcelable?, val loading: Boolean) : View.BaseSavedState(superSaveState), Parcelable

class DrawingView: View{

    var size: Int = 0
    var i: Int = 0
    var j: Int = 0


    set(value) {
        field = value
        invalidate()
    }

    var onTapCellListener: OnTapCellListener? = null
    var parkData: ArrayList<Array<String?>?>? = null

    private val paint: Paint = Paint()

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init {
        setWillNotDraw(false)

    }

    private var loading: Boolean = false

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        val bundle = Bundle()
        bundle.putParcelable("superState", superState)
        bundle.putBoolean("loading", loading)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val bundle = (state as? Bundle)
        val superState = bundle?.getParcelable<Parcelable>("superState")
        super.onRestoreInstanceState(superState ?: state)
        loading = bundle?.getBoolean("loading", false) ?: false
    }

    @SuppressLint("ClickableViewAccessibility")
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
//        var myRectList = arrayListOf<DrawingView>()


        val sizeH = height/size.toFloat()


        if(parkData != null && parkData!!.size > 0)
            if(parkData!![0]!![0]!!.equals(0)){
                val x = sizeH * parkData!!.lastIndex
                val y = sizeH * parkData!!.lastIndex
                canvas.drawRect(x, y, x + sizeH , y + sizeH, paint)
            }

       for (j in 0 until size)
           for (i in 0 until size)
           {
               val x = sizeH * i
               val y = sizeH * j
               paint.color = Color.WHITE
               canvas.drawRect(x, y, x + sizeH , y + sizeH, paint)
           }

        val x = sizeH * i
        val y = sizeH * j
        paint.color = Color.rgb((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt())
        canvas.drawRect(x, y, x + sizeH , y + sizeH, paint)

    }

    fun setValue(park_data: ArrayList<Array<String?>?>?) {
        this.parkData = park_data
        invalidate()
    }

    interface OnTapCellListener {

        fun onTapCell(i: Int, j: Int)

    }

}