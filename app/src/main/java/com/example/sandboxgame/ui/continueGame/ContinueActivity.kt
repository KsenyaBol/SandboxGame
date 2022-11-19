package com.example.sandboxgame.ui.continueGame

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.*
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.App.Companion.database
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.music.MusicService
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

class ContinueActivity : BaseActivity(R.layout.activity_continue), ContinueView {

    companion object {

        fun createLauncher() = createActivityLauncher()

    }

    override val presenter: ContinuePresenter by providePresenter()
    private var player: MediaPlayer =  MediaPlayer()
    private val musicService: MusicService = MusicService()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            presenter.onButtonContinueClicked()

            GlobalScope.launch {

                val list = database.spaceDao.getSpaceWithPlanetAndFood()

                withContext(Dispatchers.Main) {
                    showToast(list.toString().toText())
                    list

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

//            database.spaceDao.getSpaceWithPlanetAndFood()

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

    override fun showGameInfo(command: ContinuePresenter.ContinueCommand) {
        val currentDate: Date = Date()
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateText: String = dateFormat.format(currentDate)
        val timeText: String = timeFormat.format(currentDate)

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