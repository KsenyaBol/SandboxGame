package com.example.sandboxgame.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.space.Space
import com.example.core.rule.ui.objects.space.SpaceListener
import com.example.sandboxgame.R
import kotlinx.parcelize.Parcelize

@Parcelize
class MyState(private val superSaveState: Parcelable?) : View.BaseSavedState(superSaveState), Parcelable

class DrawingView : View, SpaceListener {

    var size: Int = 0
    var pause_flg = true
    var millis: Int = 200
    var drawable: Drawable = resources.getDrawable(R.drawable.planet_1)
    val imageInfect: Drawable  = resources.getDrawable(R.drawable.image_infect)

    var animation1 = (resources.getDrawable(R.drawable.animation_boom) as AnimationDrawable).also {
        it.callback = this
    }

    var onTapCellListener: OnTapCellListener? = null

    var space: Space? = null
    set(value) {
        field = value
        field?.spaceListener = this
    }

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init {
        setWillNotDraw(false)
//        cellMoving()

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
                val sizeH = height / size.toFloat()

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

        val sizeH = height / size.toFloat()

        if (space!!.myFoodList.size > 0) {
            space!!.myFoodList.forEach { food ->
                val x = sizeH * food.x
                val y = sizeH * food.y
                val satiety = food.satiety

                when(satiety) {
                    1 -> {
                        food.foodImage.setBounds(
                            (x + sizeH/2 - 5).toInt(),
                            (y + sizeH/2 - 5).toInt(),
                            (x + sizeH/2 + 5).toInt(),
                            (y + sizeH/2 + 5).toInt()
                        )
                        food.foodImage.draw(canvas)
                    }
                    5 -> {
                        food.foodImage.setBounds(
                            (x + sizeH/2 - 8).toInt(),
                            (y + sizeH/2 - 8).toInt(),
                            (x + sizeH/2 + 8).toInt(),
                            (y + sizeH/2 + 8).toInt()
                        )
                        food.foodImage.draw(canvas)
                    }
                    10 -> {
                        food.foodImage.setBounds(
                            (x + sizeH/2 - 10).toInt(),
                            (y + sizeH/2 - 10).toInt(),
                            (x + sizeH/2 + 10).toInt(),
                            (y + sizeH/2 + 10).toInt()
                        )
                        food.foodImage.draw(canvas)
                    }
                    20 -> {
                        food.foodImage.setBounds(
                            (x + sizeH/2 - 14).toInt(),
                            (y + sizeH/2 - 14).toInt(),
                            (x + sizeH/2 + 14).toInt(),
                            (y + sizeH/2 + 14).toInt()
                        )
                        food.foodImage.draw(canvas)
                    }
                }

            }
        }

        if (space!!.myPlanetList.size > 0) {
            space!!.myPlanetList.forEach { planet ->
                val x = sizeH * planet.x
                val y = sizeH * planet.y
                val infect = planet.planetInfect

                if (infect) {

                    imageInfect.setBounds(
                        (x - 10).toInt(),
                        (y - 10).toInt(),
                        ((x + 10) + sizeH).toInt(),
                        ((y + 10) + sizeH).toInt()
                    )
                    imageInfect.draw(canvas)

                    planet.planetImage.setBounds(x.toInt(), y.toInt(), (x + sizeH).toInt() , (y + sizeH).toInt())
                    planet.planetImage.draw(canvas)

                }
                else {

                    planet.planetImage.setBounds(x.toInt(), y.toInt(), (x + sizeH).toInt(), (y + sizeH).toInt())
                    planet.planetImage.draw(canvas)

                }
            }
        }

    }

//    fun cellMoving() {
//        val handler = Handler(Looper.getMainLooper())
//        var runnable: Runnable? = null
//
//        runnable = Runnable {
//            if (pause_flg == true) {
//                space.myPlanetList.forEachIndexed { index, planet ->
//
//                    var x = planet.x
//                    var y = planet.y
//                    val rand = (0..3).random()
//
//                    when (rand) {
//                        0 -> {
//                            if (x + 1 > size - 1) {
//                                x -= 1
//                            } else x += 1
//                        }
//                        1 -> {
//                            if (y + 1 > size - 1) {
//                                y -= 1
//                            } else y += 1
//                        }
//                        2 -> {
//                            if (x - 1 < 0) {
//                                x += 1
//                            } else x -= 1
//                        }
//                        3 -> {
//                            if (y - 1 < 0) {
//                                y += 1
//                            } else y -= 1
//                        }
//                    }
//
//                    space.myPlanetList[index] = Planet(x = x, y = y, planetImage = planet.planetImage,
//                        planetInfect = planet.planetInfect, satiety = planet.satiety )
//                    space.planetSatiety(x, y)
//                    space.planetDecay(x, y , planetImage = planet.planetImage, planetInfect = planet.planetInfect, satiety =
//                    planet.satiety)
//
//                }
//                invalidate()
//            }
//            handler.postDelayed(runnable!!, millis.toLong())
//        }
//        handler.postDelayed(runnable, millis.toLong())
//    }

    interface OnTapCellListener {

        fun onTapCell(i: Int, j: Int)

    }

    override fun changeSpace(space: Space) {

        invalidate()

    }


}