package com.example.sandboxgame.ui.continueGame

import com.example.sandboxgame.ui.game.GameActivity
import com.example.sandboxgame.ui.main.MainActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class ContinuePresenter: OmegaPresenter<ContinueView>() {

     var size: Int = 10

    fun onButtonBackClicked() {
        exit()
    }

    fun onButtonSaveClicked() {
        MainActivity.createLauncher().launch()
    }

    fun onButtonContinueClicked() {
        GameActivity.createLauncher(size).launch()

    }

    fun onButtonSave1Clicked() {
        viewState.showGameInfo(ContinueCommand.SAVE_1)
    }

    fun onButtonSave2Clicked() {
        viewState.showGameInfo(ContinueCommand.SAVE_2)
    }

    fun onButtonSave3Clicked() {
        viewState.showGameInfo(ContinueCommand.SAVE_3)
    }

    fun onButtonSave4Clicked() {
        viewState.showGameInfo(ContinueCommand.SAVE_4)
    }

    fun onButtonSave5Clicked() {
        viewState.showGameInfo(ContinueCommand.SAVE_5)
    }

    enum class ContinueCommand{
        SAVE_1, SAVE_2, SAVE_3, SAVE_4, SAVE_5
    }
}