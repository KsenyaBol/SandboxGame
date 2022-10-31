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
    lateinit var space: Space
//    lateinit var nameWorld: String
    private var gameActivity: GameActivity = GameActivity()

    init {
        viewState.size = size
    }

    fun onButtonNoClicked() {
        exit()
    }

    fun onButtonYesClicked() {
        ContinueActivity.createLauncher().launch()

//        val currentDate: Date = Date()
//        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//        val timeFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
//        val dateText: String = dateFormat.format(currentDate)
//        val timeText: String = timeFormat.format(currentDate)

//        continueActivity.saveGameInfo(nameWorld, timeText, dateText)
//        gameActivity.db.worldGameInfoDao?.insertWorld(WorldGameInfo(nameWorld, timeText, dateText,
//            space.id))
//
//        gameActivity.db.worldGameInfoDao?.getSpaceWithPlanetAndFood()

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