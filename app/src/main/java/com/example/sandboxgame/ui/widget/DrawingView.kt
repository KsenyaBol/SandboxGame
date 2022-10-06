package com.example.sandboxgame.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.core.rule.ui.objects.Cell
import com.example.sandboxgame.R
import kotlinx.parcelize.Parcelize

@Parcelize
class MyState(private val superSaveState: Parcelable?) : View.BaseSavedState(superSaveState), Parcelable

class DrawingView: View{

    var size: Int = 0

    var onTapCellListener: OnTapCellListener? = null
    var myDeleteRectList: ArrayList<Array<Int>> = arrayListOf()
    var myCellList: ArrayList<Cell> = arrayListOf()

    private val paint: Paint = Paint()
    private val paintBorder: Paint = Paint()
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init {
        setWillNotDraw(false)
        cellMoving()

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

    @SuppressLint("DrawAllocation")
    public override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val sizeH = height/size.toFloat()

        paint.pathEffect = CornerPathEffect(10f)
        paintBorder.pathEffect = CornerPathEffect(10f)


        if(myCellList.size > 0) {
            myCellList.forEach { array->
                val x = sizeH * array.x
                val y = sizeH * array.y
                val infect = array.cellInfect

                paint.color = array.cellColor

                paintBorder.style = Paint.Style.STROKE
                paintBorder.strokeWidth = 3f
                paintBorder.color = Color.WHITE
                canvas.drawRect(x, y, x + sizeH, y + sizeH, paintBorder)

                if (infect == true) {

                    canvas.drawRect(x, y, x + sizeH, y + sizeH, paint)

                    paint.color = Color.BLACK
                    canvas.drawCircle((x + sizeH/2F), (y + sizeH/2F), 10F, paint)

                    paintBorder.style = Paint.Style.STROKE
                    paintBorder.strokeWidth = 1f
                    paintBorder.color = Color.WHITE
                    canvas.drawCircle((x + sizeH/2F), (y + sizeH/2F), 10F, paintBorder)

                } else {
                    canvas.drawRect(x, y, x + sizeH, y + sizeH, paint)
                }


//                canvas.drawRect(x, y, x + sizeH, y + sizeH, paint)



            }

        }

//        val cellInfect = myCellList.firstOrNull { cell ->
//            cell.cellInfect == true
//        }
//        if (cellInfect == null) {
//
//            myCellInfectList.forEach { array ->
//                val x = sizeH * array[0]
//                val y = sizeH * array[1]
//                paint.color = Color.BLACK
//                canvas.drawCircle((x + sizeH/2F), (y + sizeH/2F), 10F, paint)
//
//                paintBorder.style = Paint.Style.STROKE
//                paintBorder.strokeWidth = 1f
//                paintBorder.color = Color.WHITE
//                canvas.drawCircle((x + sizeH/2F), (y + sizeH/2F), 10F, paintBorder)
//
//            }
//
//        }

    }

    fun setValue( myDeleteRectList: ArrayList<Array<Int>>, myCellList: ArrayList<Cell>) {
        this.myDeleteRectList = myDeleteRectList
        this.myCellList = myCellList
        invalidate()
    }

//    fun setValue(myCellList: ArrayList<Cell>) {
//        this.myCellList = myCellList
//        invalidate()
//    }

    fun addValue(i: Int, j: Int, cellColor: Int, cellInfect: Boolean) {
        val cell = myCellList.firstOrNull { cell ->
            cell.x == i && cell.y == j
        }
        if (cell != null) {
            myCellList.forEachIndexed { index, cell ->
                cell.x == i && cell.y == j
                myCellList[index] = Cell(x = i, y = j, cellColor = cellColor, cellInfect = true)
            }
        } else {
            myCellList.add(Cell(x = i, y = j, cellColor = cellColor, cellInfect = cellInfect))
        }

        invalidate()
    }

    fun addValue(cell: Cell) {
        val last = myCellList.lastIndex
        myCellList[last] = cell
        invalidate()
    }

    fun cellMoving() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null
        runnable = Runnable {
            myCellList.forEachIndexed { index, cell ->

                var x = cell.x
                var y = cell.y
                val rand = (0..3).random()

                when (rand) {
                    0 -> {
                        if (x + 1 > size - 1) {
                            x -= 1
                        } else x += 1
                    }
                    1 -> {
                        if (y + 1 > size - 1) {
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


                myCellList[index] = Cell(x = x, y = y, cellColor = cell.cellColor, cellInfect = cell.cellInfect)
                invalidate()
            }
            handler.postDelayed(runnable!!, 200)

        }
        handler.postDelayed(runnable, 200)

    }

    fun deleteValue(i: Int, j: Int) {
        val cell = myCellList.firstOrNull { cell ->
            cell.x == i && cell.y == j
        }
        myCellList.remove(cell)
        myDeleteRectList.add(arrayOf(i, j))

//        val cellInfect = myCellInfectList.firstOrNull { cell ->
//            cell[0] == i && cell[1] == j
//        }
//        myCellInfectList.remove(cellInfect)

        invalidate()
    }
//
//    fun infectValue(i: Int, j: Int) {
//        myCellList.add(arrayOf(i, j))
//        invalidate()
//    }
//
//    fun treatValue(i: Int, j: Int) {
//        myCellInfectList.remove(arrayOf(i, j))
//        invalidate()
//    }


    interface OnTapCellListener {

        fun onTapCell(i: Int, j: Int)

    }


}