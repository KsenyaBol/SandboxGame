package com.example.sandboxgame.ui.continueGame

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.sandboxgame.R
import com.example.core.rule.ui.database.WorldGameInfo
import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.music.MusicService
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter

class ContinueActivity: BaseActivity(R.layout.activity_continue), ContinueView {

    companion object {

        fun createLauncher() = createActivityLauncher()
    }

    override val presenter: ContinuePresenter by providePresenter()
    private var player: MediaPlayer =  MediaPlayer()
    private val musicService: MusicService = MusicService()
    lateinit var space: Space

    private val buttonBack: ImageView by bind(R.id.button_back)
    private val buttonSave1: ImageButton by bind(R.id.save_1)
    private val buttonSave2: ImageButton by bind(R.id.save_2)
    private val buttonSave3: ImageButton by bind(R.id.save_3)
    private val buttonSave4: ImageButton by bind(R.id.save_4)
    private val buttonSave5: ImageButton by bind(R.id.save_5)
    private val worldName1: TextView by bind(R.id.world_name_1)
    private val worldName2: TextView by bind(R.id.world_name_2)
    private val worldName3: TextView by bind(R.id.world_name_3)
    private val worldName4: TextView by bind(R.id.world_name_4)
    private val worldName5: TextView by bind(R.id.world_name_5)
    private val textTimeSave1: TextView by bind(R.id.text_time_save_1)
    private val textTimeSave2: TextView by bind(R.id.text_time_save_2)
    private val textTimeSave3: TextView by bind(R.id.text_time_save_3)
    private val textTimeSave4: TextView by bind(R.id.text_time_save_4)
    private val textTimeSave5: TextView by bind(R.id.text_time_save_5)
    private val textDateSave1: TextView by bind(R.id.text_date_save_1)
    private val textDateSave2: TextView by bind(R.id.text_date_save_2)
    private val textDateSave3: TextView by bind(R.id.text_date_save_3)
    private val textDateSave4: TextView by bind(R.id.text_date_save_4)
    private val textDateSave5: TextView by bind(R.id.text_date_save_5)

    private var commandSave = SaveCommand.SAVE_1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        musicService.player = player

        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        buttonBack.setOnClickListener {
            presenter.onButtonBackClicked()

            soundButtonClick.start()
        }

        buttonSave1.setOnClickListener {
            commandSave = SaveCommand.SAVE_1
            it.isSelected = true
            buttonSave2.isSelected = false
            buttonSave3.isSelected = false
            buttonSave4.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave2.setOnClickListener {
            commandSave = SaveCommand.SAVE_2
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave3.isSelected = false
            buttonSave4.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave3.setOnClickListener {
            commandSave = SaveCommand.SAVE_3
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave2.isSelected = false
            buttonSave4.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave4.setOnClickListener {
            commandSave = SaveCommand.SAVE_4
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave2.isSelected = false
            buttonSave3.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave5.setOnClickListener {
            commandSave = SaveCommand.SAVE_5
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

    fun saveGameInfo(time: String, date: String) {
        if (commandSave == SaveCommand.SAVE_1) {
            worldName1.text = "name"
            textTimeSave1.text = time
            textDateSave1.text = date
        }
        if (commandSave == SaveCommand.SAVE_2) {
            worldName2.text = "name"
            textTimeSave2.text = time
            textDateSave2.text = date
        }
        if (commandSave == SaveCommand.SAVE_3) {
            worldName3.text = "name"
            textTimeSave3.text = time
            textDateSave3.text = date
        }
        if (commandSave == SaveCommand.SAVE_4) {
            worldName4.text = "name"
            textTimeSave4.text = time
            textDateSave4.text = date
        }
        if (commandSave == SaveCommand.SAVE_5) {
            worldName5.text = "name"
            textTimeSave5.text = time
            textDateSave5.text = date
        }

//        WorldGameInfo(name, time, date, space.id)
    }

}