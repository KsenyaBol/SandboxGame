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
    var animation = arrayListOf(
        Anim(image = resources.getDrawable(R.drawable.animation_boom)),
//        Anim(image = resources.getDrawable(R.drawable.boom_stage_2)),
//        Anim(image = resources.getDrawable(R.drawable.boom_stage_3)),
//        Anim(image = resources.getDrawable(R.drawable.boom_stage_4)),
//        Anim(image = resources.getDrawable(R.drawable.boom_stage_5)),
//        Anim(image = resources.getDrawable(R.drawable.boom_stage_6)),
//        Anim(image = resources.getDrawable(R.drawable.boom_stage_7)),

    )
    data class Anim(
        val image: Drawable,
            )


    var onTapCellListener: OnTapCellListener? = null

    private val paint: Paint = Paint()
    private val paintBorder: Paint = Paint()
    lateinit var space: Space

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init {
        setWillNotDraw(false)
        cellMoving()
//        animationBlast()
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

        if (space.myPlanetList.size > 0) {
            space.myPlanetList.forEach { planet ->
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

//                    if (animation.size > 0) {
//                        animation.forEachIndexed { index, anim ->
//                            val anim = anim.image
//                            anim.setBounds(x.toInt(), y.toInt(), (x + sizeH).toInt(), (y + sizeH).toInt())
//                            anim.draw(canvas)
//
//
//                        }
//                        invalidate()
//                    }
                }
            }
        }

    }

//    fun animationBlast() {
//        val handlerAnim = Handler(Looper.getMainLooper())
//        var runnableAnim: Runnable? = null
//        runnableAnim = Runnable {
//
//            handlerAnim.postDelayed(runnableAnim!!, 300)
//            animation.remove(Anim(resources.getDrawable(R.drawable.boom_stage_1)))
//            animation.add(Anim(resources.getDrawable(R.drawable.boom_stage_2)))
//            invalidate()
//            handlerAnim.postDelayed(runnableAnim!!, 300)
//            animation.remove(Anim(resources.getDrawable(R.drawable.boom_stage_2)))
//            animation.add(Anim(resources.getDrawable(R.drawable.boom_stage_3)))
//            invalidate()
//            handlerAnim.postDelayed(runnableAnim!!, 300)
//            animation.remove(Anim(resources.getDrawable(R.drawable.boom_stage_3)))
//            animation.add(Anim(resources.getDrawable(R.drawable.boom_stage_4)))
//            invalidate()
//            handlerAnim.postDelayed(runnableAnim!!, 300)
//            animation.remove(Anim(resources.getDrawable(R.drawable.boom_stage_4)))
//            animation.add(Anim(resources.getDrawable(R.drawable.boom_stage_5)))
//            invalidate()
//            handlerAnim.postDelayed(runnableAnim!!, 300)
//            animation.remove(Anim(resources.getDrawable(R.drawable.boom_stage_5)))
//            animation.add(Anim(resources.getDrawable(R.drawable.boom_stage_6)))
//            invalidate()
//            handlerAnim.postDelayed(runnableAnim!!, 300)
//            animation.remove(Anim(resources.getDrawable(R.drawable.boom_stage_6)))
//            animation.add(Anim(resources.getDrawable(R.drawable.boom_stage_7)))
//            handlerAnim.postDelayed(runnableAnim!!, 300)
//            invalidate()
//        }
//
//    }

    fun cellMoving() {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        runnable = Runnable {
            if (pause_flg == true) {
                space.myPlanetList.forEachIndexed { index, planet ->

                    var x = planet.x
                    var y = planet.y
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

                    space.myPlanetList[index] = Planet(x = x, y = y, planetImage = planet.planetImage, planetInfect = planet.planetInfect)

                }
                invalidate()
            }
            handler.postDelayed(runnable!!, millis.toLong())
        }
        handler.postDelayed(runnable, millis.toLong())
    }

    interface OnTapCellListener {

        fun onTapCell(i: Int, j: Int)

    }

    override fun changeSpace(space: Space) {

        invalidate()

    }


}