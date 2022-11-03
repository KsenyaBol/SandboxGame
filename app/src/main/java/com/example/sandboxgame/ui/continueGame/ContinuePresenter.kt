package com.example.sandboxgame.ui.continueGame

import com.example.sandboxgame.ui.game.GameActivity
import com.example.sandboxgame.ui.main.MainActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class ContinuePresenter: OmegaPresenter<ContinueView>() {

    fun onButtonBackClicked() {
        exit()
    }
    fun onButtonSave1Clicked() {
        GameActivity.createLauncher(10).launch()
    }

    fun onButtonSave2Clicked() {
        GameActivity.createLauncher(10).launch()
    }

    fun onButtonSave3Clicked() {
        GameActivity.createLauncher(10).launch()
    }

    fun onButtonSave4Clicked() {
        GameActivity.createLauncher(10).launch()
    }

    fun onButtonSave5Clicked() {
        GameActivity.createLauncher(10).launch()
    }
}