package com.example.sandboxgame.ui.game


import android.os.Bundle
import android.widget.Button
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.widget.DrawingView
import com.omega_r.libs.omegatypes.Text
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.libs.omegalaunchers.tools.put
import com.omegar.mvp.ktx.providePresenter

open class GameActivity : BaseActivity(R.layout.activity_game), GameView, DrawingView.OnTapCellListener {

    companion object {
        private const val EXTRA_SIZE = "size"

        fun createLauncher(size: Int) = createActivityLauncher(
            EXTRA_SIZE put size,
        )
    }

    override val presenter: GamePresenter by providePresenter {
        GamePresenter(this[EXTRA_SIZE]!!)
    }
    private val buttonExit: Button by bind(R.id.button_exit)
    private val buttonAdd: Button by bind(R.id.button_add_square)
    private val buttonDelete: Button by bind(R.id.button_delete_square)
    private val buttonInfect: Button by bind(R.id.button_infect_square)
    private val drawingView: DrawingView by bind(R.id.drawing_view) {
        onTapCellListener = this@GameActivity
    }

    override var size: Int = 0
        set(value) {
            field = value
            drawingView.size = value
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buttonExit.setOnClickListener {
            presenter.onButtonExitClicked()
        }
        buttonAdd.setOnClickListener {
            presenter.onButtonAddClicked()
        }
        buttonDelete.setOnClickListener {
            presenter.onButtonDeleteClicked()
        }
        buttonInfect.setOnClickListener {
            presenter.onButtonInfectClicked()
        }
    }

    override fun onTapCell(i: Int, j: Int) {
        showToast(Text.from("i = $i, j = $j"))
    }


}