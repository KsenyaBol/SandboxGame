package com.example.sandboxgame.ui.continueGame

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        musicService.player = player

        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        buttonBack.setOnClickListener {
            presenter.onButtonBackClicked()

            soundButtonClick.start()
        }
    }

}