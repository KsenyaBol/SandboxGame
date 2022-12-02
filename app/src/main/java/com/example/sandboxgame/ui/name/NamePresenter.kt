package com.example.sandboxgame.ui.name

import com.example.sandboxgame.ui.game.GameView
import com.example.sandboxgame.ui.size.SizeFragment
import com.omega_r.base.mvp.presenters.OmegaPresenter

class NamePresenter: OmegaPresenter<NameView>() {

    fun onButtonOkClicked() {
        SizeFragment.createLauncher().launch()
        exit()
    }
}