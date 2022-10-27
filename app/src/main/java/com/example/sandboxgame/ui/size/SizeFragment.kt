package com.example.sandboxgame.ui.size

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter

open class SizeFragment : BaseActivity(R.layout.layout_field_size), SizeView {

    companion object {

        fun createLauncher() = createActivityLauncher()
    }

    override val presenter: SizePresenter by providePresenter()
    private val buttonBigField: Button by bind(R.id.button_size_big)
    private val buttonNormField: Button by bind(R.id.button_size_normal)
    private val buttonSmallField: Button by bind(R.id.button_size_small)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        buttonBigField.setOnClickListener {
            it.isSelected = true
            buttonNormField.isSelected = false
            buttonSmallField.isSelected = false
            presenter.onButtonBigClicked()

            soundButtonClick.start()
        }
        buttonNormField.setOnClickListener {
            it.isSelected = true
            buttonBigField.isSelected = false
            buttonSmallField.isSelected = false
            presenter.onButtonNormClicked()

            soundButtonClick.start()
        }
        buttonSmallField.setOnClickListener {
            it.isSelected = true
            buttonBigField.isSelected = false
            buttonNormField.isSelected = false
            presenter.onButtonSmallClicked()

            soundButtonClick.start()
        }
    }

}