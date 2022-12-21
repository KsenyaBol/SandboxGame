package com.example.sandboxgame.ui.game

import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.ui.continueGame.ContinueActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class GamePresenter(private val size: Int, private val space: Space, private val id: Int): OmegaPresenter<GameView>() {

    var command: Command? = null

    init {
        viewState.size = size
        viewState.space = space
        viewState.id = id

    }

    fun onButtonNoClicked() {
        exit()
    }

    fun onButtonYesClicked() {
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

//    fun querySaveDataBase() {
//        ContinueActivity.saveTheGame()
//    }

    enum class Command {
        ADD, DELETE, INFECT, TREAT, ADD_FOOD
    }
}