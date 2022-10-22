package com.example.sandboxgame.ui.`continue`

import com.example.sandboxgame.ui.main.MainActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class ContinuePresenter: OmegaPresenter<ContinueView>() {

    fun onButtonBackClicked() {
        MainActivity.createLauncher().launch()
        exit()
    }

}