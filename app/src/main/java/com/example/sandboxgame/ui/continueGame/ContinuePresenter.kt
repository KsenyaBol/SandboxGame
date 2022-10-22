package com.example.sandboxgame.ui.continueGame

import com.example.sandboxgame.ui.main.MainActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class ContinuePresenter: OmegaPresenter<ContinueView>() {

    fun onButtonBackClicked() {
        exit()
    }

}