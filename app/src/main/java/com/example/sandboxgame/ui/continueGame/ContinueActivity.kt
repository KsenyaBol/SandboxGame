package com.example.sandboxgame.ui.continueGame

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.sandboxgame.R
import com.example.core.rule.ui.objects.space.Space
import com.example.core.rule.ui.objects.space.SpaceObject
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.game.GameActivity
import com.example.sandboxgame.ui.music.MusicService
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ContinueActivity: BaseActivity(R.layout.activity_continue), ContinueView {

    companion object {

        fun createLauncher() = createActivityLauncher()

    }

    override val presenter: ContinuePresenter by providePresenter()
    private var player: MediaPlayer =  MediaPlayer()
    private val musicService: MusicService = MusicService()
    private val gameActivity: GameActivity = GameActivity()
    lateinit var space: Space

    private val buttonBack: ImageView by bind(R.id.button_back)
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

        buttonSave1.setOnClickListener {
            saveGameInfo(ContinueCommand.SAVE_1)
            it.isSelected = true
            buttonSave2.isSelected = false
            buttonSave3.isSelected = false
            buttonSave4.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave2.setOnClickListener {
            saveGameInfo(ContinueCommand.SAVE_2)
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave3.isSelected = false
            buttonSave4.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave3.setOnClickListener {
            saveGameInfo(ContinueCommand.SAVE_3)
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave2.isSelected = false
            buttonSave4.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave4.setOnClickListener {
            saveGameInfo(ContinueCommand.SAVE_4)
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave2.isSelected = false
            buttonSave3.isSelected = false
            buttonSave5.isSelected = false

            soundButtonClick.start()
        }

        buttonSave5.setOnClickListener {
            saveGameInfo(ContinueCommand.SAVE_5)
            it.isSelected = true
            buttonSave1.isSelected = false
            buttonSave2.isSelected = false
            buttonSave3.isSelected = false
            buttonSave4.isSelected = false

            soundButtonClick.start()
        }


    }

    enum class ContinueCommand{
        SAVE_1, SAVE_2, SAVE_3, SAVE_4, SAVE_5
    }

    fun saveGameInfo(command: ContinueCommand) {
        if (command == ContinueCommand.SAVE_1) {
//            gameActivity.db.space?.getSpaceWithPlanetAndFood("SELECT id from SpaceObject")
//            gameActivity.db.space?.getSpaceWithPlanetAndFood()
            presenter.onButtonSave1Clicked()
        }
        if (command == ContinueCommand.SAVE_2) {
//            gameActivity.db.space?.getSpaceWithPlanetAndFood()
            presenter.onButtonSave2Clicked()
        }
        if (command == ContinueCommand.SAVE_3) {
//            gameActivity.db.space?.getSpaceWithPlanetAndFood()
            presenter.onButtonSave3Clicked()
        }
        if (command == ContinueCommand.SAVE_4) {
//            gameActivity.db.space?.getSpaceWithPlanetAndFood()
            presenter.onButtonSave4Clicked()
        }
        if (command == ContinueCommand.SAVE_5) {
//            gameActivity.db.space?.getSpaceWithPlanetAndFood()
            presenter.onButtonSave5Clicked()
        }

//        WorldGameInfo(name, time, date, space.id)
    }

}