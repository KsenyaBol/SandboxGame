package com.example.sandboxgame.ui.game

import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.ui.gameSave.GameSaveActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class GamePresenter(private val size: Int): OmegaPresenter<GameView>() {

    var command: Command? = null
    lateinit var space: Space
//    lateinit var nameWorld: String

    init {
        viewState.size = size
    }

    fun onButtonNoClicked() {
        exit()
    }

    fun onButtonYesClicked() {
        GameSaveActivity.createLauncher().launch()
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
        ADD, DELETE, INFECT, TREAT, ADD_FOOD
    }
}