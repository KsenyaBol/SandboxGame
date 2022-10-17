package com.example.sandboxgame.ui.game

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.example.core.rule.ui.move.PlanetMoving
import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.widget.DrawingView
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.libs.omegalaunchers.tools.put
import com.omegar.mvp.ktx.providePresenter

class GameActivity : BaseActivity(R.layout.activity_game), GameView, DrawingView.OnTapCellListener {

    companion object {
        private const val EXTRA_SIZE = "size"

        fun createLauncher(size: Int) = createActivityLauncher(
            EXTRA_SIZE put size,
        )
    }

    lateinit var planetImage: Drawable
    var infect = 0
    var delete = 0
    var clanAmount = 0
    @SuppressLint("UseCompatLoadingForDrawables")
    private val handler = Handler()
    private var sound: Command? = null
    private val space: Space = Space()
    private val planetMoving: PlanetMoving = PlanetMoving()

    override val presenter: GamePresenter by providePresenter {
        GamePresenter(this[EXTRA_SIZE]!!)
    }
    private val buttonExit: Button by bind(R.id.button_exit)
    private val buttonAdd: Button by bind(R.id.button_add_square)
    private val buttonDelete: Button by bind(R.id.button_delete_square)
    private val buttonInfect: Button by bind(R.id.button_infect_square)
    private val buttonTreat: Button by bind(R.id.button_treat_square)
    private val buttonReproduction: ImageButton by bind(R.id.button_reproduction)
    private val buttonSlowly: ImageView by bind(R.id.button_slowly)
    private val buttonAcceleration: ImageView by bind(R.id.button_acceleration)
    private val textNumberAmount: TextView by bind(R.id.number_amount_square)
    private val textNumberAmountDied: TextView by bind(R.id.number_amount_of_died)
    private val textNumberAmountInfected: TextView by bind(R.id.number_amount_of_infected)
    private val textNumberAmountClans: TextView by bind(R.id.number_amount_of_clans)
    private val colorConstraint: View by bind(R.id.color_constraint)
    private val planet1: ImageView by bind(R.id.cell_rec_blue)
    private val planet2: ImageView by bind(R.id.cell_rec_lime)
    private val planet3: ImageView by bind(R.id.cell_rec_light_purple)
    private val planet4: ImageView by bind(R.id.cell_rec_pink)
    private val planet5: ImageView by bind(R.id.cell_rec_quartz)
    private val planet6: ImageView by bind(R.id.cell_rec_red)
    private val planet7: ImageView by bind(R.id.cell_rec_orange)
    private val planet8: ImageView by bind(R.id.cell_rec_yellow)
    private val planet9: ImageView by bind(R.id.cell_rec_green)
    private val planet10: ImageView by bind(R.id.cell_rec_cloudy_blue)

    private val soundAddCell by lazy {MediaPlayer.create(this, R.raw.add_cell)}
    private val soundDeleteCell by lazy { MediaPlayer.create(this, R.raw.delete_cell)}
    private val soundInfectCell by lazy { MediaPlayer.create(this, R.raw.infect)}
    private val soundTreatCell by lazy { MediaPlayer.create(this, R.raw.treat_cell)}

    private val drawingView: DrawingView by bind(R.id.drawing_view) {
        onTapCellListener = this@GameActivity
    }

    override var size: Int = 0
        set(value) {
            field = value
            drawingView.size = value
            planetMoving.size = value
        }



    @SuppressLint("ResourceType", "NewApi", "UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val recycleView: RecyclerView? = findViewById(R.id.cell_rec_blue)

//        val planetImageList = listOf(
//            (R.drawable.planet_1),
//            (R.drawable.planet_2),
//            (R.drawable.planet_3),
//            (R.drawable.planet_4),
//            (R.drawable.planet_5),
//            (R.drawable.planet_6),
//            (R.drawable.planet_7),
//            (R.drawable.planet_8),
//            (R.drawable.planet_9),
//            (R.drawable.planet_10),
//        )

        drawingView.space = space
//        drawingView.animation = animation
//                    animation.draw(canvas)
//        planetMoving.space = space

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        soundInfectCell.setVolume(100f,100f)


        planetImage = resources.getDrawable(R.drawable.planet_1)

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

        planet1.setOnClickListener {
            planet1.isSelected = true
            planet2.isSelected = false
            planet3.isSelected = false
            planet4.isSelected = false
            planet5.isSelected = false
            planet6.isSelected = false
            planet7.isSelected = false
            planet8.isSelected = false
            planet9.isSelected = false
            planet10.isSelected = false

            planetImage = it.background.constantState!!.newDrawable().mutate()
        }

        planet2.setOnClickListener {
            planet1.isSelected = false
            planet2.isSelected = true
            planet3.isSelected = false
            planet4.isSelected = false
            planet5.isSelected = false
            planet6.isSelected = false
            planet7.isSelected = false
            planet8.isSelected = false
            planet9.isSelected = false
            planet10.isSelected = false

            planetImage = it.background.constantState!!.newDrawable().mutate()
        }

        planet3.setOnClickListener {
            planet1.isSelected = false
            planet2.isSelected = false
            planet3.isSelected = true
            planet4.isSelected = false
            planet5.isSelected = false
            planet6.isSelected = false
            planet7.isSelected = false
            planet8.isSelected = false
            planet9.isSelected = false
            planet10.isSelected = false


            planetImage = it.background.constantState!!.newDrawable().mutate()
        }

        planet4.setOnClickListener {
            planet1.isSelected = false
            planet2.isSelected = false
            planet3.isSelected = false
            planet4.isSelected = true
            planet5.isSelected = false
            planet6.isSelected = false
            planet7.isSelected = false
            planet8.isSelected = false
            planet9.isSelected = false
            planet10.isSelected = false


            planetImage = it.background.constantState!!.newDrawable().mutate()
        }

        planet5.setOnClickListener {
            planet1.isSelected = false
            planet2.isSelected = false
            planet3.isSelected = false
            planet4.isSelected = false
            planet5.isSelected = true
            planet6.isSelected = false
            planet7.isSelected = false
            planet8.isSelected = false
            planet9.isSelected = false
            planet10.isSelected = false

            planetImage = it.background.constantState!!.newDrawable().mutate()
        }

        planet6.setOnClickListener {
            planet1.isSelected = false
            planet2.isSelected = false
            planet3.isSelected = false
            planet4.isSelected = false
            planet5.isSelected = false
            planet6.isSelected = true
            planet7.isSelected = false
            planet8.isSelected = false
            planet9.isSelected = false
            planet10.isSelected = false

            planetImage = it.background.constantState!!.newDrawable().mutate()
        }

        planet7.setOnClickListener {
            planet1.isSelected = false
            planet2.isSelected = false
            planet3.isSelected = false
            planet4.isSelected = false
            planet5.isSelected = false
            planet6.isSelected = false
            planet7.isSelected = true
            planet8.isSelected = false
            planet9.isSelected = false
            planet10.isSelected = false

            planetImage = it.background.constantState!!.newDrawable().mutate()
        }

        planet8.setOnClickListener {
            planet1.isSelected = false
            planet2.isSelected = false
            planet3.isSelected = false
            planet4.isSelected = false
            planet5.isSelected = false
            planet6.isSelected = false
            planet7.isSelected = false
            planet8.isSelected = true
            planet9.isSelected = false
            planet10.isSelected = false

            planetImage = it.background.constantState!!.newDrawable().mutate()
        }

        planet9.setOnClickListener {
            planet1.isSelected = false
            planet2.isSelected = false
            planet3.isSelected = false
            planet4.isSelected = false
            planet5.isSelected = false
            planet6.isSelected = false
            planet7.isSelected = false
            planet8.isSelected = false
            planet9.isSelected = true
            planet10.isSelected = false

            planetImage = it.background.constantState!!.newDrawable().mutate()
        }

        planet10.setOnClickListener {
            planet1.isSelected = false
            planet2.isSelected = false
            planet3.isSelected = false
            planet4.isSelected = false
            planet5.isSelected = false
            planet6.isSelected = false
            planet7.isSelected = false
            planet8.isSelected = false
            planet9.isSelected = false
            planet10.isSelected = true

            planetImage = it.background.constantState!!.newDrawable().mutate()
        }

    }

    private fun soundGame(sound: Command) {
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
            val cell = space.myPlanetList.firstOrNull { planet ->
                planet.x == i && planet.y == j
            }
            if (cell == null) {
                space.addValue(i, j, planetImage, false)
                soundGame(Command.ADD)
                drawingView.animationBlast()
            }

        }
        if (buttonDelete.isSelected) {
            val cell = space.myPlanetList.firstOrNull { planet ->
                planet.x == i && planet.y == j
            }
            if (cell != null) {
                space.deleteValue(i, j)
                delete += 1
                soundGame(Command.DELETE)
            }
        }
        if (buttonInfect.isSelected) {
            val cell = space.myPlanetList.firstOrNull { planet ->
                planet.x == i && planet.y == j && planet.planetInfect == false
            }
            if (cell != null) {
                space.infectValue(i, j,  true)
                infect += 1
                soundGame(Command.INFECT)
            }
        }

        if (buttonTreat.isSelected) {
            val cell = space.myPlanetList.firstOrNull { planet ->
                planet.x == i && planet.y == j && planet.planetInfect == true
            }
            if (cell != null) {
                space.treatValue(i, j, false)
                infect -= 1
                soundGame(Command.TREAT)
            }
        }

        val planetAmount = space.myPlanetList.size
        textNumberAmount.text = planetAmount.toString()
        textNumberAmountDied.text = delete.toString()
        textNumberAmountInfected.text = infect.toString()

        // don't work
//        val cellClansAmount = space.myCellList.count { it.cellColor >= 4 }
//        textNumberAmountClans.text = cellClansAmount.toString()
//        space.myCellList.count { it.cellColor > 0 }
    }



}
