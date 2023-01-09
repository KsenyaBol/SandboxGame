package com.example.sandboxgame.ui.game

import com.example.core.rule.ui.objects.space.Space
import com.example.data.objectDao.food.FoodEntity
import com.example.data.objectDao.planet.PlanetEntity
import com.example.sandboxgame.ui.App
import com.example.sandboxgame.ui.continueGame.ContinueActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GamePresenter(private val space: Space): OmegaPresenter<GameView>() {

    var command: Command? = null

    init {
        viewState.space = space
    }

    fun onButtonNoClicked() {
        exit()
    }

    fun onButtonYesClicked() {
        ContinueActivity.createLauncher().launch()

        exit()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun saveClicked() {
        GlobalScope.launch(Dispatchers.Main) {
            space.myPlanetList.forEach { planet ->
                val planetEntity = PlanetEntity.fromPlanet(planet)
                App.database.planetDao.insertPlanet(planetEntity)
            }
            space.myFoodList.forEach { food ->
                val foodEntity = FoodEntity.fromFood(food)
                App.database.foodDao.insertFood(foodEntity)
            }
            App.database.spaceDao.insertSpace(space)
        }
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