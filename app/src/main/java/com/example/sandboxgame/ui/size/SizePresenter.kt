package com.example.sandboxgame.ui.size

import com.example.core.rule.ui.objects.space.Space.Companion.space
import com.example.sandboxgame.ui.game.GameActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class SizePresenter: OmegaPresenter<SizeView>() {

    fun onButtonBigClicked() {
        GameActivity.createLauncher(50, space).launch()
        exit()
    }

    fun onButtonNormClicked() {
        GameActivity.createLauncher(20, space).launch()
        exit()
    }

    fun onButtonSmallClicked() {
        GameActivity.createLauncher(10, space).launch()
        exit()
    }


}