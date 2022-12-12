package com.example.sandboxgame.ui.continueGame

import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.ui.game.GameActivity
import com.example.sandboxgame.ui.main.MainActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class ContinuePresenter(): OmegaPresenter<ContinueView>() {

    var size: Int = 10

    fun onButtonBackClicked() {
        MainActivity.createLauncher().launch()
        exit()
    }

    fun onButtonSaveClicked() {
        MainActivity.createLauncher().launch()
        exit()
    }

    fun onButtonContinueClicked(space: Space, id: Int) {
        GameActivity.createLauncher(size, space, id).launch()
        exit()
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