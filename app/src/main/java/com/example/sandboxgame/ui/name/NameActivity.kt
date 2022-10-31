package com.example.sandboxgame.ui.name

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.sandboxgame.R
import com.example.core.rule.ui.database.WorldGameInfo
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.game.GameActivity
import com.example.sandboxgame.ui.game.GamePresenter
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter

class NameActivity: BaseActivity(R.layout.activity_name), NameView {

    companion object {
        fun createLauncher() = createActivityLauncher()
    }
    override val presenter: NamePresenter by providePresenter()

    private val buttonOk: Button by bind(R.id.button_ok)
    private val name: EditText by bind(R.id.text_name_world)


    private val worldGameInfo: WorldGameInfo? = null
    private val gameActivity: GameActivity = GameActivity()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        gameActivity.nameWorld = name.toString()

        val worldName = name.text.toString()
        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        buttonOk.setOnClickListener {
            presenter.onButtonOkClicked()

            worldGameInfo?.nameWorld += worldName

            soundButtonClick.start()
        }
    }
}