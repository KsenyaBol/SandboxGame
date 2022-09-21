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
import com.example.sandboxgame.R
import kotlinx.parcelize.Parcelize

@Parcelize
class MyState(private val superSaveState: Parcelable?, val loading: Boolean) : View.BaseSavedState(superSaveState), Parcelable

class DrawingView: View{

    var size: Int = 0

    set(value) {
        field = value
        invalidate()
    }

    var onTapCellListener: OnTapCellListener? = null
    var myRectList: ArrayList<Array<Int>> = arrayListOf()
    var myDeleteRectList: ArrayList<Array<Int>> = arrayListOf()
    var myCellList: ArrayList<Cell> = arrayListOf()
    var myCellInfectList: ArrayList<Array<Int>> = arrayListOf()

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

    public override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val sizeH = height/size.toFloat()

//        paint.color = Color.WHITE
//        canvas.drawRect(0f, 0f, height.toFloat(),  height.toFloat(), paint)

        if(myCellList.size > 0) {
            myCellList.forEach { array->
                val x = sizeH * array.x
                val y = sizeH * array.y

                paint.color = array.cellColor

                canvas.drawRect(x, y, x + sizeH, y + sizeH, paint)
            }

        }

        if(myCellInfectList.size > 0) {
            myCellInfectList.forEach { array ->
                val x = sizeH * array[0]
                val y = sizeH * array[1]
                paint.color = Color.BLACK
                canvas.drawCircle((x + sizeH/2F), (y + sizeH/2F), 10F, paint)

            }
        }

    }

    fun setValue(myRectList: ArrayList<Array<Int>>, myDeleteRectList: ArrayList<Array<Int>>, myCellInfectList: ArrayList<Array<Int>>) {
        this.myRectList = myRectList
        this.myDeleteRectList = myDeleteRectList
        this.myCellInfectList = myCellInfectList
        invalidate()
    }

    fun setValue(myCellList: ArrayList<Cell>) {
        this.myCellList = myCellList
        invalidate()
    }

    fun addValue(i: Int, j: Int, cellColor: Int) {
        myCellList.add(Cell(x = i, y = j, cellColor = cellColor))
        invalidate()
    }

    fun deleteValue(i: Int, j: Int) {
        val cell = myCellList.firstOrNull { cell ->
            cell.x == i && cell.y == j
        }
        myCellList.remove(cell)
        myDeleteRectList.add(arrayOf(i, j))

//        myCellList.count { it.cellColor == Color.RED }

        val cellInfect = myCellInfectList.firstOrNull { cell ->
            cell[0] == i && cell[1] == j
        }
        myCellInfectList.remove(cellInfect)
        myDeleteRectList.add(arrayOf(i, j))

        invalidate()
    }

    fun infectValue(i: Int, j: Int) {
        myCellInfectList.add(arrayOf(i, j))
        invalidate()
    }

    interface OnTapCellListener {

        fun onTapCell(i: Int, j: Int)

    }

    data class Cell(
        var x: Int,
        var y: Int,
        var cellColor: Int,
        )

}