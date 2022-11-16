package com.example.sandboxgame.ui.gameSave

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.continueGame.ContinueActivity
import com.example.sandboxgame.ui.music.MusicService
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class GameSaveActivity: BaseActivity(R.layout.activity_game_save), GameSaveView {

    companion object {

        fun createLauncher() = createActivityLauncher()

    }

    override val presenter: GameSavePresenter by providePresenter()

    private var player: MediaPlayer =  MediaPlayer()
    private val musicService: MusicService = MusicService()
    lateinit var space: Space

    private val buttonSave: ImageView by bind(R.id.button_save)
    private val buttonSave1: ImageButton by bind(R.id.save_1)
    private val buttonSave2: ImageButton by bind(R.id.save_2)
    private val buttonSave3: ImageButton by bind(R.id.save_3)
    private val buttonSave4: ImageButton by bind(R.id.save_4)
    private val buttonSave5: ImageButton by bind(R.id.save_5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        musicService.player = player

        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        buttonSave.setOnClickListener {
            presenter.onButtonSaveClicked()

            val text = "world successfully saved"
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(applicationContext, text, duration).show()

            soundButtonClick.start()
        }

        buttonSave1.setOnClickListener {
            saveGameInfo(SaveCommand.SAVE_1)
            it.isSelected = true
            buttonSave2.isSelected = false
            buttonSave3.isSelected = false
            buttonSave4.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave2.setOnClickListener {
            saveGameInfo(SaveCommand.SAVE_2)
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave3.isSelected = false
            buttonSave4.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave3.setOnClickListener {
            saveGameInfo(SaveCommand.SAVE_3)
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave2.isSelected = false
            buttonSave4.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave4.setOnClickListener {
            saveGameInfo(SaveCommand.SAVE_4)
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave2.isSelected = false
            buttonSave3.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave5.setOnClickListener {
            saveGameInfo(SaveCommand.SAVE_5)
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave2.isSelected = false
            buttonSave3.isSelected = false
            buttonSave4.isSelected = false

            soundButtonClick.start()
        }


    }

    enum class SaveCommand{
        SAVE_1, SAVE_2, SAVE_3, SAVE_4, SAVE_5
    }

    fun saveGameInfo(command: SaveCommand) {
        val currentDate: Date = Date()
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateText: String = dateFormat.format(currentDate)
        val timeText: String = timeFormat.format(currentDate)
        if (command == SaveCommand.SAVE_1) {
            ContinueActivity().textTimeSave1.text = timeText
            ContinueActivity().textDateSave1.text = dateText
        }
        if (command == SaveCommand.SAVE_2) {
            ContinueActivity().textTimeSave2.text = timeText
            ContinueActivity().textDateSave2.text = dateText
        }
        if (command == SaveCommand.SAVE_3) {
            ContinueActivity().textTimeSave3.text = timeText
            ContinueActivity().textDateSave3.text = dateText
        }
        if (command == SaveCommand.SAVE_4) {
            ContinueActivity().textTimeSave4.text = timeText
            ContinueActivity().textDateSave4.text = dateText
        }
        if (command == SaveCommand.SAVE_5) {
            ContinueActivity().textTimeSave5.text = timeText
            ContinueActivity().textDateSave5.text = dateText
        }

    }


}