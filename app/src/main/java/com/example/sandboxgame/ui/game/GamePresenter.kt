package com.example.sandboxgame.ui.game

import androidx.compose.runtime.saveable.autoSaver
import com.example.core.rule.ui.objects.space.Space
import com.example.data.objectDao.food.FoodEntity
import com.example.data.objectDao.planet.PlanetEntity
import com.example.data.objectDao.space.SpaceEntity
import com.example.sandboxgame.ui.App
import com.example.sandboxgame.ui.App.Companion.database
import com.example.sandboxgame.ui.continueGame.ContinueActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GamePresenter(private val space: Space): OmegaPresenter<GameView>() {

    var command: Command? = null
    private var spaceEntity: SpaceEntity = SpaceEntity(space.size)

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

            database.spaceDao.insertSpace(spaceEntity)

            space.myPlanetList.forEach { planet ->
                planet.spaceId = spaceEntity.id
                val planetEntity = PlanetEntity.fromPlanet(planet)
                database.planetDao.insertPlanet(planetEntity)
            }
            space.myFoodList.forEach { food ->
                food.spaceId = spaceEntity.id
                val foodEntity = FoodEntity.fromFood(food)
                database.foodDao.insertFood(foodEntity)
            }

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