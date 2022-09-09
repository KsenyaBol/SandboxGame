package com.example.sandboxgame.ui.size

import com.example.sandboxgame.ui.game.GameActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

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