package com.example.sandboxgame.ui.game


import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.widget.DrawingView
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.libs.omegalaunchers.tools.put
import com.omegar.mvp.ktx.providePresenter


open class GameActivity : BaseActivity(R.layout.activity_game), GameView, DrawingView.OnTapCellListener {

    companion object {
        private const val EXTRA_SIZE = "size"

        fun createLauncher(size: Int) = createActivityLauncher(
            EXTRA_SIZE put size,
        )
    }

    override val presenter: GamePresenter by providePresenter {
        GamePresenter(this[EXTRA_SIZE]!!)
    }
    private val buttonExit: Button by bind(R.id.button_exit)
    private val buttonAdd: Button by bind(R.id.button_add_square)
    private val buttonDelete: Button by bind(R.id.button_delete_square)
    private val buttonInfect: Button by bind(R.id.button_infect_square)
    private val drawingView: DrawingView by bind(R.id.drawing_view) {
        onTapCellListener = this@GameActivity
    }

    override var size: Int = 0
        set(value) {
            field = value
            drawingView.size = value
        }

    open fun parkData() : ArrayList<Array<String?>?>? {
        return parkData()
//        drawingView.setValue(park_data())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        buttonExit.setOnClickListener {
            presenter.onButtonExitClicked()

            soundButtonClick.start()
        }
        buttonAdd.setOnClickListener {
            it.isSelected = true
            buttonDelete.isSelected = false
            buttonInfect.isSelected = false
            presenter.onButtonAddClicked()

            soundButtonClick.start()
        }
        buttonDelete.setOnClickListener {
            it.isSelected = true
            buttonAdd.isSelected = false
            buttonInfect.isSelected = false
            presenter.onButtonDeleteClicked()

            soundButtonClick.start()
        }
        buttonInfect.setOnClickListener {
            it.isSelected = true
            buttonInfect.invalidate()
            buttonAdd.isSelected = false
            buttonDelete.isSelected = false
            presenter.onButtonInfectClicked()

            soundButtonClick.start()
        }
    }

    override fun onTapCell(i: Int, j: Int) {
        drawingView.i = i
        drawingView.j = j
    }

//    private var myDrawingObjects: ArrayList<DrawingView>? = null
//    open fun MyGFXSur(context: Context?) {
//        myDrawingObjects = ArrayList<DrawingView>()
//
////        myDrawingObjects!!.add(MyRect(this@MainActivity))
//    }
//
//
//    open fun run() {
//        while (isRunning) {
//            if (!holder.getSurface().isValid()) {
//                continue
//            }
//            updatePhysics()
//            val canvas: Canvas = holder.lockCanvas()
//            drawObjects(canvas)
//            holder.unlockCanvasAndPost(canvas)
//        }
//    }
//
//    private open fun updatePhysics() {
//        for (drawObject in myDrawingObjects) {
//            drawObject.updatePhysics()
//        }
//    }
//
//    private open fun drawObjects(canvas: Canvas) {
//        for (drawObject in myDrawingObjects) {
//            drawObject.draw(canvas)
//        }
//    }

}