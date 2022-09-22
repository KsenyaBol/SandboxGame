package com.example.sandboxgame.ui.main

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.music.MusicService
import com.example.sandboxgame.ui.music.ScreenReceiver
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter


open class MainActivity : BaseActivity(R.layout.activity_main), MainView {

    companion object {

        fun createLauncher() = createActivityLauncher()
    }
    override val presenter: MainPresenter by providePresenter()
    private val buttonStart: Button by bind(R.id.button_start)
    private val buttonSetting: Button by bind(R.id.button_settings)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        val receiver: BroadcastReceiver = ScreenReceiver()
        registerReceiver(receiver, filter)


        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        startService(Intent(this, MusicService::class.java))
        stopService(Intent(this, MusicService::class.java))

        buttonStart.setOnClickListener {
           presenter.onButtonStartClicked()
            soundButtonClick.start()
        }

        buttonSetting.setOnClickListener {
            presenter.onButtonSettingsClicked()
            soundButtonClick.start()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        stopService(Intent(this, MusicService::class.java))
    }

    override fun onClickView(view: View) {
        super.onClickView(view)
    }

}