package com.example.sandboxgame.ui.game

import android.os.Bundle
import android.widget.Button
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.omega_r.libs.omegatypes.Text
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.libs.omegalaunchers.tools.put
import com.omegar.mvp.ktx.providePresenter

open class GameActivity : BaseActivity(R.layout.activity_game), GameView {

    companion object {
        private const val EXTRA_SIZE = "size"

        fun createLauncher(size: Int) = createActivityLauncher(
            EXTRA_SIZE put size
        )
    }

    override val presenter: GamePresenter by providePresenter {
        GamePresenter(this[EXTRA_SIZE]!!)
    }
    private val buttonExit: Button by bind(R.id.button_exit)

    override var size: Int = 0
        set(value) {
            field = value
            showToast(Text.from("$value"))
//             drawingView.size = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buttonExit.setOnClickListener {
            presenter.onButtonExitClicked()
        }
    }



}