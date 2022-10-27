package com.example.sandboxgame.ui.game

import com.example.sandboxgame.ui.continueGame.ContinueActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class GamePresenter(private val size: Int): OmegaPresenter<GameView>() {

    private var command: Command? = null

    init {
        viewState.size = size
    }

    fun onButtonNoClicked() {
        exit()
    }

    fun onButtonYesClicked() {
        command = Command.GAME_ACTIVITY
        ContinueActivity.createLauncher().launch()
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

    fun onButtonAddFoodClicked() {
        command = Command.ADD_FOOD
    }

    enum class Command {
        ADD, DELETE, INFECT, TREAT, ADD_FOOD, GAME_ACTIVITY
    }
}