package com.example.sandboxgame.ui.game

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.example.core.rule.ui.objects.Cell
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.widget.DrawingView
import com.omega_r.libs.extensions.context.getCompatColor
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.libs.omegalaunchers.tools.put
import com.omegar.mvp.ktx.providePresenter


open class GameActivity : BaseActivity(R.layout.activity_game), GameView, DrawingView.OnTapCellListener{

    companion object {
        private const val EXTRA_SIZE = "size"

        fun createLauncher(size: Int) = createActivityLauncher(
            EXTRA_SIZE put size,
        )
    }

    private var colorCell: Int = Color.BLACK
    var infect = 0
    var delete = 0
    private val handler = Handler()

    override val presenter: GamePresenter by providePresenter {
        GamePresenter(this[EXTRA_SIZE]!!)
    }
    private val buttonExit: Button by bind(R.id.button_exit)
    private val buttonAdd: Button by bind(R.id.button_add_square)
    private val buttonDelete: Button by bind(R.id.button_delete_square)
    private val buttonInfect: Button by bind(R.id.button_infect_square)
    private val buttonTreat: Button by bind(R.id.button_treat_square)
    private val textNumberAmount: TextView by bind(R.id.number_amount_square)
    private val textNumberAmountDied: TextView by bind(R.id.number_amount_of_died)
    private val textNumberAmountInfected: TextView by bind(R.id.number_amount_of_infected)
    private val textNumberAmountClans: TextView by bind(R.id.number_amount_of_clans)
    private val colorConstraint: View by bind(R.id.color_constraint)
    private val cellRecBlue: ImageView by bind(R.id.cell_rec_blue)
    private val cellRecLime: ImageView by bind(R.id.cell_rec_lime)
    private val cellRecLightPurple: ImageView by bind(R.id.cell_rec_light_purple)
    private val cellRecPink: ImageView by bind(R.id.cell_rec_pink)
    private val cellRecQuartz: ImageView by bind(R.id.cell_rec_quartz)
    private val cellRecRed: ImageView by bind(R.id.cell_rec_red)
    private val cellRecOrange: ImageView by bind(R.id.cell_rec_orange)
    private val cellRecYellow: ImageView by bind(R.id.cell_rec_yellow)
    private val cellRecGreen: ImageView by bind(R.id.cell_rec_green)
    private val cellRecCloudyBlue: ImageView by bind(R.id.cell_rec_cloudy_blue)
    private val drawingView: DrawingView by bind(R.id.drawing_view) {
        onTapCellListener = this@GameActivity
    }

    override var size: Int = 0
        set(value) {
            field = value
            drawingView.size = value
        }



    @SuppressLint("ResourceType", "NewApi")
    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)
        colorCell = getCompatColor(R.color.blue_gray)

//        startViewAnimation()

        buttonExit.setOnClickListener {
            presenter.onButtonExitClicked()

            soundButtonClick.start()
        }

        colorConstraint.isVisible = false

        buttonAdd.setOnClickListener {
            presenter.onButtonAddClicked()
            it.isSelected = true
            buttonDelete.isSelected = false
            buttonInfect.isSelected = false
            buttonTreat.isSelected = false
            colorConstraint.isVisible = true

            soundButtonClick.start()

        }
        buttonDelete.setOnClickListener {
            presenter.onButtonDeleteClicked()
            it.isSelected = true
            buttonAdd.isSelected = false
            buttonInfect.isSelected = false
            buttonTreat.isSelected = false
            colorConstraint.isVisible = false

            soundButtonClick.start()
        }
        buttonInfect.setOnClickListener {
            presenter.onButtonInfectClicked()
            it.isSelected = true
            buttonAdd.isSelected = false
            buttonDelete.isSelected = false
            buttonTreat.isSelected = false
            colorConstraint.isVisible = false

            soundButtonClick.start()
        }
        buttonTreat.setOnClickListener {
            presenter.onButtonTreatClicked()
            it.isSelected = true
            buttonAdd.isSelected = false
            buttonDelete.isSelected = false
            buttonInfect.isSelected = false
            colorConstraint.isVisible = false

            soundButtonClick.start()
        }

        cellRecBlue.setOnClickListener {
            cellRecBlue.isSelected = true
            cellRecLime.isSelected = false
            cellRecLightPurple.isSelected = false
            cellRecPink.isSelected = false
            cellRecQuartz.isSelected = false
            cellRecRed.isSelected = false
            cellRecOrange.isSelected = false
            cellRecYellow.isSelected = false
            cellRecGreen.isSelected = false
            cellRecCloudyBlue.isSelected = false

            val colorBlue = resources.getColor(R.color.blue_gray)
            colorCell = colorBlue
        }

        cellRecLime.setOnClickListener {
            cellRecBlue.isSelected = false
            cellRecLime.isSelected = true
            cellRecLightPurple.isSelected = false
            cellRecPink.isSelected = false
            cellRecQuartz.isSelected = false
            cellRecRed.isSelected = false
            cellRecOrange.isSelected = false
            cellRecYellow.isSelected = false
            cellRecGreen.isSelected = false
            cellRecCloudyBlue.isSelected = false

            val colorLime = resources.getColor(R.color.lime)
            colorCell = colorLime
        }

        cellRecLightPurple.setOnClickListener {
            cellRecBlue.isSelected = false
            cellRecLime.isSelected = false
            cellRecLightPurple.isSelected = true
            cellRecPink.isSelected = false
            cellRecQuartz.isSelected = false
            cellRecRed.isSelected = false
            cellRecOrange.isSelected = false
            cellRecYellow.isSelected = false
            cellRecGreen.isSelected = false
            cellRecCloudyBlue.isSelected = false

            val colorPurple = resources.getColor(R.color.light_purple)
            colorCell = colorPurple
        }

        cellRecPink.setOnClickListener {
            cellRecBlue.isSelected = false
            cellRecLime.isSelected = false
            cellRecLightPurple.isSelected = false
            cellRecPink.isSelected = true
            cellRecQuartz.isSelected = false
            cellRecRed.isSelected = false
            cellRecOrange.isSelected = false
            cellRecYellow.isSelected = false
            cellRecGreen.isSelected = false
            cellRecCloudyBlue.isSelected = false

            val colorPink = resources.getColor(R.color.tonyc_pink)
            colorCell = colorPink
        }

        cellRecQuartz.setOnClickListener {
            cellRecBlue.isSelected = false
            cellRecLime.isSelected = false
            cellRecLightPurple.isSelected = false
            cellRecPink.isSelected = false
            cellRecQuartz.isSelected = true
            cellRecRed.isSelected = false
            cellRecOrange.isSelected = false
            cellRecYellow.isSelected = false
            cellRecGreen.isSelected = false
            cellRecCloudyBlue.isSelected = false

            val colorQuartz = resources.getColor(R.color.quartz)
            colorCell = colorQuartz
        }

        cellRecRed.setOnClickListener {
            cellRecBlue.isSelected = false
            cellRecLime.isSelected = false
            cellRecLightPurple.isSelected = false
            cellRecPink.isSelected = false
            cellRecQuartz.isSelected = false
            cellRecRed.isSelected = true
            cellRecOrange.isSelected = false
            cellRecYellow.isSelected = false
            cellRecGreen.isSelected = false
            cellRecCloudyBlue.isSelected = false

            val colorCherry = resources.getColor(R.color.cherry)
            colorCell = colorCherry
        }

        cellRecOrange.setOnClickListener {
            cellRecBlue.isSelected = false
            cellRecLime.isSelected = false
            cellRecLightPurple.isSelected = false
            cellRecPink.isSelected = false
            cellRecQuartz.isSelected = false
            cellRecRed.isSelected = false
            cellRecOrange.isSelected = true
            cellRecYellow.isSelected = false
            cellRecGreen.isSelected = false
            cellRecCloudyBlue.isSelected = false

            val colorOrange = resources.getColor(R.color.orange)
            colorCell = colorOrange
        }

        cellRecYellow.setOnClickListener {
            cellRecBlue.isSelected = false
            cellRecLime.isSelected = false
            cellRecLightPurple.isSelected = false
            cellRecPink.isSelected = false
            cellRecQuartz.isSelected = false
            cellRecRed.isSelected = false
            cellRecOrange.isSelected = false
            cellRecYellow.isSelected = true
            cellRecGreen.isSelected = false
            cellRecCloudyBlue.isSelected = false

            val colorYellow = resources.getColor(R.color.yellow)
            colorCell = colorYellow
        }

        cellRecGreen.setOnClickListener {
            cellRecBlue.isSelected = false
            cellRecLime.isSelected = false
            cellRecLightPurple.isSelected = false
            cellRecPink.isSelected = false
            cellRecQuartz.isSelected = false
            cellRecRed.isSelected = false
            cellRecOrange.isSelected = false
            cellRecYellow.isSelected = false
            cellRecGreen.isSelected = true
            cellRecCloudyBlue.isSelected = false

            val colorGreen = resources.getColor(R.color.green_grass)
            colorCell = colorGreen
        }

        cellRecCloudyBlue.setOnClickListener {
            cellRecBlue.isSelected = false
            cellRecLime.isSelected = false
            cellRecLightPurple.isSelected = false
            cellRecPink.isSelected = false
            cellRecQuartz.isSelected = false
            cellRecRed.isSelected = false
            cellRecOrange.isSelected = false
            cellRecYellow.isSelected = false
            cellRecGreen.isSelected = false
            cellRecCloudyBlue.isSelected = true

            val colorCloudyBlue = resources.getColor(R.color.cloudy_blue)
            colorCell = colorCloudyBlue
        }

    }



    override fun onTapCell(i: Int, j: Int) {

//        CellMoving().cellMoving(i, j, colorCell, false)

        if (buttonAdd.isSelected) {
            val cell = drawingView.myCellList.firstOrNull { cell: Cell ->
                cell.x == i && cell.y == j
            }
            if (cell == null) {
                drawingView.addValue(i, j, colorCell, false)
//                drawingView.addValue(Cell(i, j, colorCell, false))

            }

        }
        if (buttonDelete.isSelected) {
            val cell = drawingView.myCellList.firstOrNull { cell ->
                cell.x == i && cell.y == j
            }
            if (cell != null) {
                drawingView.deleteValue(i, j)
            }
        }
        if (buttonInfect.isSelected) {
            val cell = drawingView.myCellList.firstOrNull { cell ->
                cell.x == i && cell.y == j
            }
            if (cell != null) {
//                drawingView.infectValue(i, j)
                drawingView.addValue(i, j, colorCell, true)
                infect += 1
            }
        }

//        if (buttonTreat.isSelected) {
//            val cell = drawingView.myCellList.firstOrNull { cell ->
//                cell.x == i && cell.y == j && cell.cellInfect
//            }
//            if (cell != null) {
//                drawingView.treatValue(i, j)
//            }
//        }

        val cellAmount = drawingView.myCellList.size
        textNumberAmount.text = cellAmount.toString()
        val cellDieAmount = drawingView.myDeleteRectList.size
        textNumberAmountDied.text = cellDieAmount.toString()
//        val cellInfectAmount = drawingView.myCellInfectList.size
        textNumberAmountInfected.text = infect.toString()

        // don't work
//        val cellClansAmount = drawingView.myCellList.count { it.cellColor >= 4 }
//        textNumberAmountClans.text = cellClansAmount.toString()
    }



}

//    val handler = Handler(Looper.getMainLooper())
//        var runnable: Runnable? = null
//        runnable = Runnable {
//            Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
//            handler.postDelayed(runnable!!, 5000)
//        }
//
//        handler.postDelayed(runnable, 5000)
