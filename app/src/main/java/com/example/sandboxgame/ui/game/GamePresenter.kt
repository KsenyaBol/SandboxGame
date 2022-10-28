package com.example.sandboxgame.ui.game

import com.example.core.rule.ui.objects.space.Space
import com.example.core.rule.ui.database.WorldGameInfo
import com.example.sandboxgame.ui.continueGame.ContinueActivity
import com.example.sandboxgame.ui.name.NameActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class GamePresenter(private val size: Int): OmegaPresenter<GameView>() {

     var command: Command? = null
    private val continueActivity: ContinueActivity = ContinueActivity()
    private val nameActivity: NameActivity = NameActivity()
    private val gameActivity: GameActivity = GameActivity()
    lateinit var space: Space

    init {
        viewState.size = size
    }

    fun onButtonNoClicked() {
        exit()
    }

    fun onButtonYesClicked() {
        command = Command.GAME_ACTIVITY
        ContinueActivity.createLauncher().launch()

        val currentDate: Date = Date()
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val timeFormat: DateFormat = SimpleDateFormat("HH/mm", Locale.getDefault())
        val dateText: String = dateFormat.format(currentDate)
        val timeText: String = timeFormat.format(currentDate)

        continueActivity.saveGameInfo(nameActivity.name.text.toString(), timeText, dateText)
        gameActivity.db.worldGameInfoDao?.insertWorld(WorldGameInfo(nameActivity.name.text.toString(), timeText, dateText))

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