package com.example.sandboxgame.ui.`continue`

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter

class ContinueActivity: BaseActivity(R.layout.activity_continue), ContinueView {

    companion object {

        fun createLauncher() = createActivityLauncher()
    }

    override val presenter: ContinuePresenter by providePresenter()

    private val buttonBack: ImageView by bind(R.id.button_back)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        buttonBack.setOnClickListener {
            presenter.onButtonBackClicked()

            soundButtonClick.start()
        }
    }

}