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
    private val buttonBigFaild: Button by bind(R.id.button_size_big)
    private val buttonNormFaild: Button by bind(R.id.button_size_normal)
    private val buttonSmallFaild: Button by bind(R.id.button_size_small)

//    private val space: com.example.core.rule.ui.objects.space.Space = null!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        buttonBigFaild.setOnClickListener {
            it.isSelected = true
            buttonNormFaild.isSelected = false
            buttonSmallFaild.isSelected = false
            presenter.onButtonBigClicked()

            soundButtonClick.start()
        }
        buttonNormFaild.setOnClickListener {
            it.isSelected = true
            buttonBigFaild.isSelected = false
            buttonSmallFaild.isSelected = false
            presenter.onButtonNormClicked()

            soundButtonClick.start()
        }
        buttonSmallFaild.setOnClickListener {
            it.isSelected = true
            buttonBigFaild.isSelected = false
            buttonNormFaild.isSelected = false
            presenter.onButtonSmallClicked()

            soundButtonClick.start()
        }
    }

}