package com.example.sandboxgame.ui.settings

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.music.MusicService
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter
import kotlin.math.ln

class SettingsActivity: BaseActivity(R.layout.activity_settings), SettingsView, SeekBar.OnSeekBarChangeListener {

    private var player: MediaPlayer =  MediaPlayer()
    private val musicService: MusicService = MusicService()
    private val MAX_VOLUME = 100

//    private val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
//    private var initVolume: Int = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
//    private val maxVolume: Int = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

    companion object {

        fun createLauncher() = createActivityLauncher()
    }

    override val presenter: SettingsPresenter by providePresenter()

    private val seekBar: SeekBar by bind(R.id.seekbar_for_sound)
    private val textAmountProgressSeekbar: TextView by bind(R.id.text_amount_progress_seekbar)
    private val buttonBack: ImageView by bind(R.id.button_back)

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        musicService.player = player

        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

//        volumeControlStream = AudioManager.STREAM_MUSIC
//        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume, 0)


        seekBar.setOnSeekBarChangeListener(this)
        textAmountProgressSeekbar.text = "0"

        buttonBack.setOnClickListener {
            presenter.onButtonBackClicked()

            soundButtonClick.start()
        }

    }

    //уведомляет об изменении положения ползунка
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        val volume = (1 - ln((MAX_VOLUME - progress).toDouble()) / ln(MAX_VOLUME.toDouble())).toFloat()
        player.setVolume(volume, volume)

    }

    // уведомляет о том, что пользователь начал перемещать ползунок
    override fun onStartTrackingTouch(seekBar: SeekBar?) {    }

    // уведомляет о том, что пользователь закончил перемещать ползунок
    override fun onStopTrackingTouch(seekBar: SeekBar?) {

        textAmountProgressSeekbar.text = seekBar?.progress.toString()

    }

}