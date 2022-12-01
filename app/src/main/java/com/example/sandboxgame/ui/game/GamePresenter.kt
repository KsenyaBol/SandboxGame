package com.example.sandboxgame.ui.game

import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.ui.continueGame.ContinueActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

class GamePresenter(private val size: Int, private val planet: ArrayList<Planet>): OmegaPresenter<GameView>() {

    var id: Int = 0
    var command: Command? = null
//    lateinit var space: Space

    init {
        viewState.size = size
        viewState.planet = planet
    }

    fun onButtonNoClicked() {
        exit()
    }

    fun onButtonYesClicked() {
       ContinueActivity.createLauncher().launch()
        ContinueActivity.id = id
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