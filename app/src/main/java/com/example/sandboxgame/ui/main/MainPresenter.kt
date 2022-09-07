package com.example.sandboxgame.ui.main

import com.example.sandboxgame.ui.game.GameActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class MainPresenter: OmegaPresenter<MainView>() {

    fun onButtonStartClicked() {
        GameActivity.createLauncher().launch()
    }
}