package com.example.sandboxgame.ui.game

import com.omega_r.base.mvp.presenters.OmegaPresenter

class GamePresenter(private val size: Int): OmegaPresenter<GameView>() {

    private var command: Command? = null

    init {
        viewState.size = size
    }

    fun onButtonExitClicked() {
        exit()
    }

    fun onButtonAddClicked() {
        command = Command.ADD
    }

    fun onButtonDeleteClicked() {
        command = Command.DELETE
    }

    fun onButtonInfectClicked() {
        command = Command.INFECT
    }

    fun onButtonTreatClicked() {
        command = Command.TREAT
    }

    private enum class Command {
        ADD, DELETE, INFECT, TREAT
    }
}