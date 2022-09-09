package com.example.sandboxgame.ui.game

import com.example.sandboxgame.ui.base.BasePresenter
import com.example.sandboxgame.ui.main.MainActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter
import kotlinx.coroutines.launch

class GamePresenter(private val size: Int): OmegaPresenter<GameView>() {

    init {
        viewState.size = size
    }

    fun onButtonExitClicked() {
        exit()
    }
}