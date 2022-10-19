package com.example.sandboxgame.ui.game

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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
    lateinit var foodImage: Drawable
    var infect = 0
    var delete = 0
    var clanAmount = 0
    var satiety: Int = 0
    @SuppressLint("UseCompatLoadingForDrawables")
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
    private val buttonAddFood: Button by bind(R.id.button_add_food)
    private val buttonReproduction: ImageButton by bind(R.id.button_reproduction)
    private val buttonSlowly: ImageView by bind(R.id.button_slowly)
    private val buttonAcceleration: ImageView by bind(R.id.button_acceleration)
    private val textNumberAmount: TextView by bind(R.id.number_amount_square)
    private val textNumberAmountDied: TextView by bind(R.id.number_amount_of_died)
    private val textNumberAmountInfected: TextView by bind(R.id.number_amount_of_infected)
    private val textNumberAmountClans: TextView by bind(R.id.number_amount_of_clans)
    private val textNumberAmountFood: TextView by bind(R.id.number_amount_of_food)

    private val planetConstraint: View by bind(R.id.planet_constraint)
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

    private val foodConstraint: View by bind(R.id.food_constraint)
    private val foodXS: ImageView by bind(R.id.food_XS)
    private val foodS: ImageView by bind(R.id.food_S)
    private val foodM: ImageView by bind(R.id.food_M)
    private val foodL: ImageView by bind(R.id.food_L)

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

    var foodAmount: Int = 0
    set(value) {
        field = value
        space.foodAmount = value
    }


    @SuppressLint("ResourceType", "NewApi", "UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drawingView.space = space
        planetMoving.space = space

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        planetConstraint.isVisible = false
        foodConstraint.isVisible = false

        soundInfectCell.setVolume(100f,100f)

        planetImage = resources.getDrawable(R.drawable.planet_1)

        buttonExit.setOnClickListener {
            presenter.onButtonExitClicked()

            soundButtonClick.start()
        }

        buttonReproduction.setOnClickListener {

            if (planetMoving.pause_flg) {
                planetMoving.pause_flg = false

                val reproduction : Drawable? = getDrawable(R.drawable.ic_reproduction)
                buttonReproduction.setBackgroundDrawable(reproduction)

            } else {
                planetMoving.pause_flg = true

                val pause : Drawable? = getDrawable(R.drawable.ic_pause)
                buttonReproduction.setBackgroundDrawable(pause)

            }

            soundButtonClick.start()
        }

        buttonSlowly.setOnClickListener {
            it.isSelected = true
            if(planetMoving.millis < 3001) {
                planetMoving.millis += 100
            } else planetMoving.millis = 3000

            soundButtonClick.start()
        }

        buttonAcceleration.setOnClickListener {
            it.isSelected = true
            if (planetMoving.millis > 101) {
                planetMoving.millis -= 100
            } else planetMoving.millis = 0

            soundButtonClick.start()
        }

        buttonAdd.setOnClickListener {
            presenter.onButtonAddClicked()
            it.isSelected = true
            buttonDelete.isSelected = false
            buttonInfect.isSelected = false
            buttonTreat.isSelected = false
            buttonAddFood.isSelected = false
            planetConstraint.isVisible = true
            foodConstraint.isVisible = false

            soundButtonClick.start()

        }

        buttonDelete.setOnClickListener {
            presenter.onButtonDeleteClicked()
            it.isSelected = true
            buttonAdd.isSelected = false
            buttonInfect.isSelected = false
            buttonTreat.isSelected = false
            buttonAddFood.isSelected = false
            planetConstraint.isVisible = false
            foodConstraint.isVisible = false

            soundButtonClick.start()
        }

        buttonInfect.setOnClickListener {
            presenter.onButtonInfectClicked()
            it.isSelected = true
            buttonAdd.isSelected = false
            buttonDelete.isSelected = false
            buttonTreat.isSelected = false
            buttonAddFood.isSelected = false
            planetConstraint.isVisible = false
            foodConstraint.isVisible = false

            soundButtonClick.start()
        }

        buttonTreat.setOnClickListener {
            presenter.onButtonTreatClicked()
            it.isSelected = true
            buttonAdd.isSelected = false
            buttonDelete.isSelected = false
            buttonInfect.isSelected = false
            buttonAddFood.isSelected = false
            planetConstraint.isVisible = false
            foodConstraint.isVisible = false

            soundButtonClick.start()
        }

        buttonAddFood.setOnClickListener {
            presenter.onButtonAddFoodClicked()
            it.isSelected = true
            buttonAdd.isSelected = false
            buttonDelete.isSelected = false
            buttonInfect.isSelected = false
            planetConstraint.isVisible = false
            buttonTreat.isSelected = false
            planetConstraint.isVisible = false
            foodConstraint.isVisible = true

            soundButtonClick.start()

        }

        foodXS.setOnClickListener {
            foodXS.isSelected = true
            foodS.isSelected = false
            foodM.isSelected = false
            foodL.isSelected = false

            satiety = 1
            foodImage = resources.getDrawable(R.drawable.planet_food_3)
        }

        foodS.setOnClickListener {
            foodXS.isSelected = false
            foodS.isSelected = true
            foodM.isSelected = false
            foodL.isSelected = false

            satiety = 5
            foodImage = resources.getDrawable(R.drawable.planet_food_2)
        }

        foodM.setOnClickListener {
            foodXS.isSelected = false
            foodS.isSelected = false
            foodM.isSelected = true
            foodL.isSelected = false

            satiety = 10
            foodImage = resources.getDrawable(R.drawable.planet_food_1)
        }

        foodL.setOnClickListener {
            foodXS.isSelected = false
            foodS.isSelected = false
            foodM.isSelected = false
            foodL.isSelected = true

            satiety = 20
            foodImage = resources.getDrawable(R.drawable.planet_food_4)
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
            val planet = space.myPlanetList.firstOrNull { planet ->
                planet.x == i && planet.y == j
            }
            if (planet == null) {
                space.addValue(i, j, planetImage, false, 0)
                soundGame(Command.ADD)
            }

        }
        if (buttonDelete.isSelected) {
            val planet = space.myPlanetList.firstOrNull { planet ->
                planet.x == i && planet.y == j
            }
            if (planet != null) {
                space.deleteValue(i, j)
                delete += 1
                soundGame(Command.DELETE)
            }
        }
        if (buttonInfect.isSelected) {
            val planet = space.myPlanetList.firstOrNull { planet ->
                planet.x == i && planet.y == j && !planet.planetInfect
            }
            if (planet != null) {
                space.infectValue(i, j,  true)
                infect += 1
                soundGame(Command.INFECT)
            }
        }

        if (buttonTreat.isSelected) {
            val planet = space.myPlanetList.firstOrNull { planet ->
                planet.x == i && planet.y == j && planet.planetInfect
            }
            if (planet != null) {
                space.treatValue(i, j, false)
                infect -= 1
                soundGame(Command.TREAT)
            }
        }

        if (buttonAddFood.isSelected) {
            val food = space.myFoodList.firstOrNull { food ->
                food.x == i && food.y == j
            }
            if (food == null) {
                space.addFood(i, j, foodImage, satiety)
                foodAmount += 1
                soundGame(Command.ADD)
            }
        }

        val planetAmount = space.myPlanetList.size
        textNumberAmount.text = planetAmount.toString()
        textNumberAmountDied.text = delete.toString()
        textNumberAmountInfected.text = infect.toString()
        textNumberAmountFood.text = foodAmount.toString()

        // don't work
//        val cellClansAmount = space.myCellList.count { it.cellColor >= 4 }
//        textNumberAmountClans.text = cellClansAmount.toString()
//        space.myCellList.count { it.cellColor > 0 }
    }


}