package com.example.sandboxgame.ui.size

import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.ui.game.GameActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class SizePresenter: OmegaPresenter<SizeView>() {

    fun onButtonBigClicked() {
        GameActivity.createLauncher(Space(50)).launch()
        exit()
    }

    fun onButtonNormClicked() {
        GameActivity.createLauncher(Space(20)).launch()
        exit()
    }

    fun onButtonSmallClicked() {
        GameActivity.createLauncher(Space(10)).launch()
        exit()
    }


}