package com.example.sandboxgame.ui.continueGame

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.*
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.App.Companion.database
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.music.MusicService
import com.omega_r.libs.extensions.list.toArrayList
import com.omega_r.libs.omegatypes.toText
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ContinueActivity : BaseActivity(R.layout.activity_continue), ContinueView {

    companion object {
        const val APP_PREFERENCES = "mysettings"
        const val TEXT_TIME = "timeSave"
        const val TEXT_DATE = "dateSave"
        const val TEXT_TIME2 = "timeSave2"
        const val TEXT_DATE2 = "dateSave2"
        const val TEXT_TIME3 = "timeSave3"
        const val TEXT_DATE3 = "dateSave3"
        const val TEXT_TIME4 = "timeSave4"
        const val TEXT_DATE4 = "dateSave4"
        const val TEXT_TIME5 = "timeSave5"
        const val TEXT_DATE5 = "dateSave5"

        var id = 0
        fun createLauncher() = createActivityLauncher(
        )
    }

    var mSettings: SharedPreferences? = null

    override val presenter: ContinuePresenter by providePresenter()

    private var player: MediaPlayer =  MediaPlayer()
    private val musicService: MusicService = MusicService()
    private val space: Space = Space()
    var food: ArrayList<Food> = arrayListOf()
    var planet:  ArrayList<Planet> = arrayListOf()

    private val buttonBack: ImageView by bind(R.id.button_back)
    private val buttonSave: Button by bind(R.id.button_save)
    private val buttonContinue: Button by bind(R.id.button_continue)
    private val buttonSave1: ImageButton by bind(R.id.save_1)
    private val buttonSave2: ImageButton by bind(R.id.save_2)
    private val buttonSave3: ImageButton by bind(R.id.save_3)
    private val buttonSave4: ImageButton by bind(R.id.save_4)
    private val buttonSave5: ImageButton by bind(R.id.save_5)
    val textTimeSave1: TextView by bind(R.id.text_time_save_1)
    val textTimeSave2: TextView by bind(R.id.text_time_save_2)
    val textTimeSave3: TextView by bind(R.id.text_time_save_3)
    val textTimeSave4: TextView by bind(R.id.text_time_save_4)
    val textTimeSave5: TextView by bind(R.id.text_time_save_5)
    val textDateSave1: TextView by bind(R.id.text_date_save_1)
    val textDateSave2: TextView by bind(R.id.text_date_save_2)
    val textDateSave3: TextView by bind(R.id.text_date_save_3)
    val textDateSave4: TextView by bind(R.id.text_date_save_4)
    val textDateSave5: TextView by bind(R.id.text_date_save_5)

    val currentDate: Date = Date()
    val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val timeFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val dateText: String = dateFormat.format(currentDate)
    val timeText: String = timeFormat.format(currentDate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        musicService.player = player

        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        buttonBack.setOnClickListener {
            presenter.onButtonBackClicked()

            soundButtonClick.start()
        }

        buttonSave.setOnClickListener {
            presenter.onButtonSaveClicked()

            val text = "world successfully saved"
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(applicationContext, text, duration).show()

            soundButtonClick.start()
        }

        buttonContinue.setOnClickListener {
            presenter.onButtonContinueClicked(food = space.myFoodList, planet = space.myPlanetList)

            GlobalScope.launch {

                database.spaceDao.getSpaceWithPlanetAndFood()

                withContext(Dispatchers.Main) {
                    space.myFoodList = database.foodDao.getAllFood().toArrayList()
                    food = space.myFoodList
                    space.myPlanetList = database.planetDao.getAllPlanet().toArrayList()
                    planet = space.myPlanetList

                    showToast(space.myFoodList.toString().toText())
                    showToast(space.myPlanetList.toString().toText())

//                    space.myPlanetList = SpaceWithPlanetAndFood().planet!!.toArrayList()
//                    space.myFoodList = SpaceWithPlanetAndFood().food!!.toArrayList()

                }
            }

            soundButtonClick.start()
        }

        buttonSave1.setOnClickListener {
            presenter.onButtonSave1Clicked()
            it.isSelected = true
            buttonSave2.isSelected = false
            buttonSave3.isSelected = false
            buttonSave4.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave2.setOnClickListener {
            presenter.onButtonSave2Clicked()
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave3.isSelected = false
            buttonSave4.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave3.setOnClickListener {
            presenter.onButtonSave3Clicked()
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave2.isSelected = false
            buttonSave4.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave4.setOnClickListener {
            presenter.onButtonSave4Clicked()
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave2.isSelected = false
            buttonSave3.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave5.setOnClickListener {
            presenter.onButtonSave5Clicked()
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave2.isSelected = false
            buttonSave3.isSelected = false
            buttonSave4.isSelected = false

            soundButtonClick.start()
        }

    }

    override fun onPause() {
        super.onPause()
        val editor: SharedPreferences.Editor = mSettings!!.edit()

        val timeSave1: String = textTimeSave1.text.toString()
        editor.putString(TEXT_TIME, timeSave1)

        val timeSave2: String = textTimeSave2.text.toString()
        editor.putString(TEXT_TIME2, timeSave2)

        val timeSave3: String = textTimeSave3.text.toString()
        editor.putString(TEXT_TIME3, timeSave3)

        val timeSave4: String = textTimeSave4.text.toString()
        editor.putString(TEXT_TIME4, timeSave4)

        val timeSave5: String = textTimeSave5.text.toString()
        editor.putString(TEXT_TIME5, timeSave5)

        val dateSave1: String = textDateSave1.text.toString()
        editor.putString(TEXT_DATE, dateSave1)

        val dateSave2: String = textDateSave2.text.toString()
        editor.putString(TEXT_DATE2, dateSave2)

        val dateSave3: String = textDateSave3.text.toString()
        editor.putString(TEXT_DATE3, dateSave3)

        val dateSave4: String = textDateSave4.text.toString()
        editor.putString(TEXT_DATE4, dateSave4)

        val dateSave5: String = textDateSave5.text.toString()
        editor.putString(TEXT_DATE5, dateSave5)

        editor.apply()
    }

    override fun onResume() {
        super.onResume()

        if(mSettings!!.contains(TEXT_TIME)) {
            textTimeSave1.text = mSettings!!.getString(TEXT_TIME, "")
            textTimeSave2.text = mSettings!!.getString(TEXT_TIME2, "")
            textTimeSave3.text = mSettings!!.getString(TEXT_TIME3, "")
            textTimeSave4.text = mSettings!!.getString(TEXT_TIME4, "")
            textTimeSave5.text = mSettings!!.getString(TEXT_TIME5, "")
            textDateSave1.text = mSettings!!.getString(TEXT_DATE, "")
            textDateSave2.text = mSettings!!.getString(TEXT_DATE2, "")
            textDateSave3.text = mSettings!!.getString(TEXT_DATE3, "")
            textDateSave4.text = mSettings!!.getString(TEXT_DATE4, "")
            textDateSave5.text = mSettings!!.getString(TEXT_DATE5, "")
        }
    }

    override fun showGameInfo(command: ContinuePresenter.ContinueCommand) {

        if (command == ContinuePresenter.ContinueCommand.SAVE_1) {
            textTimeSave1.text = timeText
            textDateSave1.text = dateText
        }
        if (command == ContinuePresenter.ContinueCommand.SAVE_2) {
            textTimeSave2.text = timeText
            textDateSave2.text = dateText
        }
        if (command == ContinuePresenter.ContinueCommand.SAVE_3) {
            textTimeSave3.text = timeText
            textDateSave3.text = dateText
        }
        if (command == ContinuePresenter.ContinueCommand.SAVE_4) {
            textTimeSave4.text = timeText
            textDateSave4.text = dateText
        }
        if (command == ContinuePresenter.ContinueCommand.SAVE_5) {
            textTimeSave5.text = timeText
            textDateSave5.text = dateText
        }

    }


}