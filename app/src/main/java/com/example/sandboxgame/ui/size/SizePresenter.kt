package com.example.sandboxgame.ui.size

import android.content.Intent
import com.example.sandboxgame.ui.game.GameActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter
import com.omegar.libs.omegalaunchers.createActivityLauncher

class SizePresenter: OmegaPresenter<SizeView>() {

    fun onButtonBigClicked() {
        GameActivity.createLauncher(50).launch()
        exit()
    }

    fun onButtonNormClicked() {
        GameActivity.createLauncher(20).launch()
        exit()
    }

    fun onButtonSmallClicked() {
        GameActivity.createLauncher(10).launch()
        exit()
    }
}