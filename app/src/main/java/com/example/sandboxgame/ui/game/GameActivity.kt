package com.example.sandboxgame.ui.game

import android.graphics.Color
import android.location.GnssAntennaInfo
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.sandboxgame.R
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.base.BaseView
import com.omega_r.base.mvp.presenters.OmegaPresenter
import com.omegar.libs.omegalaunchers.Launcher
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter

open class GameActivity : BaseActivity(R.layout.activity_game), GameView {

    companion object{


        fun createLauncher() = createActivityLauncher()
    }

    override val presenter: GamePresenter by providePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

    }

    override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }


}