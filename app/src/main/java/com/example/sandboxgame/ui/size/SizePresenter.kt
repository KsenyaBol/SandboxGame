package com.example.sandboxgame.ui.size

import androidx.annotation.Nullable
import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.ui.game.GameActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class SizePresenter: OmegaPresenter<SizeView>() {


    fun onButtonBigClicked() {
        GameActivity.createLauncher(50, null).launch()
        exit()
    }

    fun onButtonNormClicked() {
        GameActivity.createLauncher(20, null).launch()
        exit()
    }

    fun onButtonSmallClicked() {
        GameActivity.createLauncher(10, null).launch()
        exit()
    }


}