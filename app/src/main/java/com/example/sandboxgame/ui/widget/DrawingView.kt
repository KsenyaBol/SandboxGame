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
import com.example.core.rule.ui.objects.food.ConvertFoodImage
import com.example.core.rule.ui.objects.planet.ConvertPlanetImage
import com.example.core.rule.ui.objects.space.Space
import com.example.core.rule.ui.objects.space.SpaceListener
import com.example.sandboxgame.R
import kotlinx.parcelize.Parcelize

@Parcelize
class MyState(private val superSaveState: Parcelable?) : View.BaseSavedState(superSaveState), Parcelable

class DrawingView : View, SpaceListener {

    var size: Int = 0
    @SuppressLint("UseCompatLoadingForDrawables")
    val imageInfect: Drawable  = resources.getDrawable(R.drawable.image_infect)
    @SuppressLint("UseCompatLoadingForDrawables")
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
        var planetIm = resources.getDrawable(R.drawable.planet_1)

        if (space!!.myFoodList.size > 0) {
            space!!.myFoodList.forEach { food ->
                val x = sizeH * food.x
                val y = sizeH * food.y
                val satiety = food.satiety
                var foodIm = resources.getDrawable(R.drawable.planet_food_1)

                if (food.foodImage == ConvertFoodImage.Food.FOOD_XS) {
                    foodIm = resources.getDrawable(R.drawable.planet_food_3)
                }
                if (food.foodImage == ConvertFoodImage.Food.FOOD_S) {
                    foodIm = resources.getDrawable(R.drawable.planet_food_2)
                }
                if (food.foodImage == ConvertFoodImage.Food.FOOD_M) {
                    foodIm = resources.getDrawable(R.drawable.planet_food_1)
                }
                if (food.foodImage == ConvertFoodImage.Food.FOOD_L) {
                    foodIm = resources.getDrawable(R.drawable.planet_food_4)
                }

                when(satiety) {
                    1 -> {
                        foodIm.setBounds(
                            (x + sizeH/2 - 5).toInt(),
                            (y + sizeH/2 - 5).toInt(),
                            (x + sizeH/2 + 5).toInt(),
                            (y + sizeH/2 + 5).toInt()
                        )
                        foodIm.draw(canvas)
                    }
                    5 -> {
                        foodIm.setBounds(
                            (x + sizeH/2 - 8).toInt(),
                            (y + sizeH/2 - 8).toInt(),
                            (x + sizeH/2 + 8).toInt(),
                            (y + sizeH/2 + 8).toInt()
                        )
                        foodIm.draw(canvas)
                    }
                    10 -> {
                        foodIm.setBounds(
                            (x + sizeH/2 - 10).toInt(),
                            (y + sizeH/2 - 10).toInt(),
                            (x + sizeH/2 + 10).toInt(),
                            (y + sizeH/2 + 10).toInt()
                        )
                        foodIm.draw(canvas)
                    }
                    20 -> {
                        foodIm.setBounds(
                            (x + sizeH/2 - 14).toInt(),
                            (y + sizeH/2 - 14).toInt(),
                            (x + sizeH/2 + 14).toInt(),
                            (y + sizeH/2 + 14).toInt()
                        )
                        foodIm.draw(canvas)
                    }
                }

            }
        }

        if (space!!.myPlanetList.size > 0) {
            space!!.myPlanetList.forEach { planet ->
                val x = sizeH * planet.planetX
                val y = sizeH * planet.planetY
                val infect = planet.planetInfect


                if (planet.planetImage == ConvertPlanetImage.Planet.PLANET1) {
                    planetIm = resources.getDrawable(R.drawable.planet_1)
                }
                if (planet.planetImage == ConvertPlanetImage.Planet.PLANET2) {
                    planetIm = resources.getDrawable(R.drawable.planet_2)
                }
                if (planet.planetImage == ConvertPlanetImage.Planet.PLANET3) {
                    planetIm = resources.getDrawable(R.drawable.planet_3)
                }
                if (planet.planetImage == ConvertPlanetImage.Planet.PLANET4) {
                    planetIm = resources.getDrawable(R.drawable.planet_4)
                }
                if (planet.planetImage == ConvertPlanetImage.Planet.PLANET5) {
                    planetIm = resources.getDrawable(R.drawable.planet_5)
                }
                if (planet.planetImage == ConvertPlanetImage.Planet.PLANET6) {
                    planetIm = resources.getDrawable(R.drawable.planet_6)
                }
                if (planet.planetImage == ConvertPlanetImage.Planet.PLANET7) {
                    planetIm = resources.getDrawable(R.drawable.planet_7)
                }
                if (planet.planetImage == ConvertPlanetImage.Planet.PLANET8) {
                    planetIm = resources.getDrawable(R.drawable.planet_8)
                }
                if (planet.planetImage == ConvertPlanetImage.Planet.PLANET9) {
                    planetIm = resources.getDrawable(R.drawable.planet_9)
                }
                if (planet.planetImage == ConvertPlanetImage.Planet.PLANET10) {
                    planetIm = resources.getDrawable(R.drawable.planet_10)
                }

                if (infect >= 50) {

                    imageInfect.setBounds(
                        (x - 10).toInt(),
                        (y - 10).toInt(),
                        ((x + 10) + sizeH).toInt(),
                        ((y + 10) + sizeH).toInt()
                    )
                    imageInfect.draw(canvas)

                    planetIm.setBounds(x.toInt(), y.toInt(), (x + sizeH).toInt() , (y + sizeH).toInt())
                    planetIm.draw(canvas)

                }
                else {

                    planetIm.setBounds(x.toInt(), y.toInt(), (x + sizeH).toInt(), (y + sizeH).toInt())
                    planetIm.draw(canvas)

                }
            }
        }

//        if (space!!.myPlanetList.size > 0) {
//            space!!.myPlanetList.forEach { planet ->
//                val x = sizeH * planet.planetX
//                val y = sizeH * planet.planetY
//                val age = planet.age
//
//                if (age >= 100) {
//                    animation1.setBounds(
//                        (x - 9).toInt(),
//                        (y - 9).toInt(),
//                        ((x + 9) + sizeH).toInt(),
//                        ((y + 9) + sizeH).toInt()
//                    )
//                    animation1.draw(canvas)
//
//                    planetIm.setBounds(x.toInt(), y.toInt(), (x + sizeH).toInt() , (y + sizeH).toInt())
//                    planetIm.draw(canvas)
//                }
//                else {
//
//                    planetIm.setBounds(x.toInt(), y.toInt(), (x + sizeH).toInt(), (y + sizeH).toInt())
//                    planetIm.draw(canvas)
//
//                }
//
//            }
//        }

    }

    interface OnTapCellListener {

        fun onTapCell(i: Int, j: Int)

    }

    override fun changeSpace(space: Space) {

        invalidate()

    }


}