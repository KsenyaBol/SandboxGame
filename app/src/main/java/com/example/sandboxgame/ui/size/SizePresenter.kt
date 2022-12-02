package com.example.sandboxgame.ui.size

import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.ui.game.GameActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class SizePresenter: OmegaPresenter<SizeView>() {

    fun onButtonBigClicked() {
        GameActivity.createLauncher(50, Space()).launch()
        exit()
    }

    fun onButtonNormClicked() {
        GameActivity.createLauncher(20, Space()).launch()
        exit()
    }

    fun onButtonSmallClicked() {
        GameActivity.createLauncher(10, Space()).launch()
        exit()
    }
}