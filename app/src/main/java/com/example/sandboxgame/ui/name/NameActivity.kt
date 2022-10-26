package com.example.sandboxgame.ui.name

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.sandboxgame.R
import com.example.sandboxgame.di.PersistentStorage
import com.example.sandboxgame.ui.base.BaseActivity
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter

class NameActivity: BaseActivity(R.layout.activity_name), NameView {

    companion object {
        fun createLauncher() = createActivityLauncher()
    }
    override val presenter: NamePresenter by providePresenter()

    private val buttonOk: Button by bind(R.id.button_ok)
    private val name: EditText by bind(R.id.text_name_world)
    val worldName = name.text.toString()
    private val persistentStorage: PersistentStorage = PersistentStorage()

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)

        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        buttonOk.setOnClickListener {
            presenter.onButtonOkClicked()

            persistentStorage.addProperty(worldName)
            persistentStorage.getProperty(worldName)

            soundButtonClick.start()
        }
    }
}