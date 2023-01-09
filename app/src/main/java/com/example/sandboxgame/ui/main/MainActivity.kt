package com.example.sandboxgame.ui.main

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.App
import com.example.sandboxgame.ui.App.Companion.database
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.music.MusicService
import com.example.sandboxgame.ui.music.ScreenReceiver
import com.omega_r.libs.extensions.common.ifNull
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter
import kotlinx.coroutines.*

open class MainActivity : BaseActivity(R.layout.activity_main), MainView {

    companion object {

        fun createLauncher() = createActivityLauncher()
    }
    override val presenter: MainPresenter by providePresenter()
    private val startButton: Button by bind(R.id.button_start)
    private val continueButton: Button by bind(R.id.button_continue)
    private val settingButton: Button by bind(R.id.button_settings)
    private val cancelButton: ImageView by bind(R.id.button_cancel)
    private val messageWarning: ImageView by bind(R.id.warning_window)
    private val messageWarningText: TextView by bind(R.id.warning_text)

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        messageWarning.isVisible = false
        messageWarningText.isVisible = false
        cancelButton.isVisible = false

        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        val receiver: BroadcastReceiver = ScreenReceiver()
        registerReceiver(receiver, filter)
        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        startService(Intent(this, MusicService::class.java))

        startButton.setOnClickListener {

            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    if (database.spaceDao.getAllSpace().size >= 5) {
                        messageWarning.isVisible = true
                        messageWarningText.isVisible = true
                        cancelButton.isVisible = true
                    } else {
                        presenter.onButtonStartClicked()
                    }

                }
            }

            soundButtonClick.start()
        }

        continueButton.setOnClickListener {
            presenter.onButtonContinueClicked()

            soundButtonClick.start()
        }

        settingButton.setOnClickListener {
            presenter.onButtonSettingsClicked()
            soundButtonClick.start()
        }

        cancelButton.setOnClickListener {
            messageWarning.isVisible = false
            messageWarningText.isVisible = false
            cancelButton.isVisible = false

            soundButtonClick.start()
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        stopService(Intent(this, MusicService::class.java))
    }

    override fun onClickView(view: View) {
        super.onClickView(view)
    }

}