package com.example.sandboxgame.ui.gameSave

import com.example.sandboxgame.ui.main.MainActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class GameSavePresenter: OmegaPresenter<GameSaveView>()  {

    fun onButtonSaveClicked() {
        MainActivity.createLauncher().launch()
        exit()
    }

}