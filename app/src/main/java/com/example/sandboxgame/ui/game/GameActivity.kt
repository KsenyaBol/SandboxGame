package com.example.sandboxgame.ui.game

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.example.core.rule.ui.objects.Cell
import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.widget.DrawingView
import com.omega_r.libs.extensions.context.getCompatColor
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.libs.omegalaunchers.tools.put
import com.omegar.mvp.ktx.providePresenter

class GameActivity : BaseActivity(R.layout.activity_game), GameView, DrawingView.OnTapCellListener{

    companion object {
        private const val EXTRA_SIZE = "size"

        fun createLauncher(size: Int) = createActivityLauncher(
            EXTRA_SIZE put size,
        )
    }

    private var colorCell: Int = Color.BLACK
    var infect = 0
    var delete = 0
    var clanAmount = 0
    private val handler = Handler()
    private var sound: Command? = null

    override val presenter: GamePresenter by providePresenter {
        GamePresenter(this[EXTRA_SIZE]!!)
    }
    private val buttonExit: Button by bind(R.id.button_exit)
    private val buttonAdd: Button by bind(R.id.button_add_square)
    private val buttonDelete: Button by bind(R.id.button_delete_square)
    private val buttonInfect: Button by bind(R.id.button_infect_square)
    private val buttonTreat: Button by bind(R.id.button_treat_square)
//    private val buttonPause: ImageButton by bind(R.id.button_pause)
    private val buttonReproduction: ImageButton by bind(R.id.button_reproduction)
    private val buttonSlowly: ImageView by bind(R.id.button_slowly)
    private val buttonAcceleration: ImageView by bind(R.id.button_acceleration)
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

    private val soundAddCell by lazy {MediaPlayer.create(this, R.raw.add_cell)}
    private val soundDeleteCell by lazy { MediaPlayer.create(this, R.raw.delete_cell)}
    private val soundInfectCell by lazy { MediaPlayer.create(this, R.raw.infect)}
    private val soundTreatCell by lazy { MediaPlayer.create(this, R.raw.treat_cell)}


    override var size: Int = 0
        set(value) {
            field = value
            drawingView.size = value
//            Space().size = value
        }



    @SuppressLint("ResourceType", "NewApi", "UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        if (Space().change == true) {
            drawingView.invalidate()
        }

        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)


        colorCell = getCompatColor(R.color.blue_gray)

        buttonExit.setOnClickListener {
            presenter.onButtonExitClicked()

            soundButtonClick.start()
        }

        buttonReproduction.setOnClickListener {

            if (drawingView.pause_flg == true) {
                drawingView.pause_flg = false

                val reproduction : Drawable? = getDrawable(R.drawable.ic_reproduction)
                buttonReproduction.setBackgroundDrawable(reproduction)

            } else {
                drawingView.pause_flg = true

                val pause : Drawable? = getDrawable(R.drawable.ic_pause)
                buttonReproduction.setBackgroundDrawable(pause)

            }

            soundButtonClick.start()
        }

        buttonSlowly.setOnClickListener {
            it.isSelected = true
            if(drawingView.millis < 3001) {
                drawingView.millis += 100
            } else drawingView.millis = 3000

            soundButtonClick.start()
        }

        buttonAcceleration.setOnClickListener {
            it.isSelected = true
            if (drawingView.millis > 101) {
                drawingView.millis -= 100
            } else drawingView.millis = 0

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

//        @SuppressLint("UseCompatLoadingForDrawables")
//    fun pauseGame (button_reproduction: ImageButton) {
//        if (drawingView.pause_flg == false) {
//            drawingView.pause_flg = true
//
//            val reproduction : Drawable? = getDrawable(R.drawable.ic_reproduction)
//            buttonReproduction.setBackgroundDrawable(reproduction)
//
//        } else {
//            drawingView.pause_flg = false
//
//            val pause : Drawable? = getDrawable(R.drawable.ic_pause)
//            buttonReproduction.setBackgroundDrawable(pause)
//
//        }
//    }

    fun soundGame(sound: Command) {
        if (sound == Command.ADD) {
            soundAddCell.start()
        }
        if (sound == Command.DELETE) {
            soundDeleteCell.start()
        }
        if (sound == Command.INFECT) {
            soundInfectCell.start()
        }
        if (sound == Command.TREAT) {
            soundTreatCell.start()
        }
    }

    enum class Command {
        ADD, DELETE, INFECT, TREAT
    }

    override fun onTapCell(i: Int, j: Int) {


        if (buttonAdd.isSelected) {
            val cell = drawingView.myCellList.firstOrNull { cell: Cell ->
                cell.x == i && cell.y == j
            }
            if (cell == null) {
//                Space().addValue(i, j, colorCell, false)
                drawingView.addValue(i, j, colorCell, false)
                soundGame(Command.ADD)
            }

        }
        if (buttonDelete.isSelected) {
            val cell = drawingView.myCellList.firstOrNull { cell ->
                cell.x == i && cell.y == j
            }
            if (cell != null) {
//                Space().deleteValue(i, j)
                drawingView.deleteValue(i, j)
                delete += 1
                soundGame(Command.DELETE)
            }
        }
        if (buttonInfect.isSelected) {
            val cell = drawingView.myCellList.firstOrNull { cell ->
                cell.x == i && cell.y == j
            }
            if (cell != null) {
//                Space().addValue(i, j, colorCell, true)
                drawingView.infectValue(i, j,  true)
                infect += 1
                soundGame(Command.INFECT)
            }
        }

        if (buttonTreat.isSelected) {
            val cell = drawingView.myCellList.firstOrNull { cell ->
                cell.x == i && cell.y == j && cell.cellInfect
            }
            if (cell != null) {
//                Space().treatValue(i, j, colorCell, false)
                drawingView.treatValue(i, j, false)
                soundGame(Command.TREAT)
            }
        }

        drawingView.myCellList.count { it.cellColor > 0 }

        val cellAmount = drawingView.myCellList.size
        textNumberAmount.text = cellAmount.toString()
        textNumberAmountDied.text = delete.toString()
        textNumberAmountInfected.text = infect.toString()

        // don't work
//        val cellClansAmount = drawingView.myCellList.count { it.cellColor >= 4 }
//        textNumberAmountClans.text = cellClansAmount.toString()
    }

//    @SuppressLint("UseCompatLoadingForDrawables")
//    fun pauseGame(view: View) {
//        buttonReproduction.setOnClickListener {
//
//            if (drawingView.pause_flg == true) {
//                drawingView.pause_flg = false
//
//                val reproduction : Drawable? = getDrawable(R.drawable.ic_reproduction)
//                buttonReproduction.setBackgroundDrawable(reproduction)
//
//            } else {
//                drawingView.pause_flg = true
//
//                val pause : Drawable? = getDrawable(R.drawable.ic_pause)
//                buttonReproduction.setBackgroundDrawable(pause)
//
//            }
//        }
//
//    }


}
