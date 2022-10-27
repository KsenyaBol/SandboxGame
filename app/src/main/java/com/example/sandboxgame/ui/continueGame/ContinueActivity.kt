package com.example.sandboxgame.ui.continueGame

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.sandboxgame.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        musicService.player = player

        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        buttonBack.setOnClickListener {
            presenter.onButtonBackClicked()

            soundButtonClick.start()
        }

        buttonSave1.setOnClickListener {

        }


    }

}