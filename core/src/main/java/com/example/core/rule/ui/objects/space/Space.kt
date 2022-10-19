package com.example.core.rule.ui.objects.space

import android.graphics.drawable.Drawable
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.planet.Planet
import java.util.*
import kotlin.collections.ArrayList

class Space {

    var myPlanetList: ArrayList<Planet> = arrayListOf()
    var myFoodList: ArrayList<Food> = arrayListOf()
    var spaceListener: SpaceListener? = null
    var foodAmount: Int = 0

    fun setValue(myPlanetList: ArrayList<Planet>, spaceListener: SpaceListener, myFoodList: ArrayList<Food>) {

        this.myPlanetList = myPlanetList
        this.spaceListener = spaceListener
        this.myFoodList = myFoodList

    }

    fun addValue(i: Int, j: Int, planetImage: Drawable, planetInfect: Boolean, satiety: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
        }
        if (planet == null) {
            myPlanetList.add(Planet(x = i, y = j, planetImage = planetImage, planetInfect = planetInfect, satiety = satiety))
            spaceListener?.changeSpace(space = this)
        }

    }

    fun infectValue(i: Int, j: Int, planetInfect: Boolean) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
        }
        if (planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList[index] = Planet(x = i, y = j, planetImage = planet.planetImage, planetInfect = planetInfect,
                satiety = planet.satiety)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun deleteValue(i: Int, j: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
        }
        myPlanetList.remove(planet)
        spaceListener?.changeSpace(space = this)

    }

    fun treatValue(i: Int, j: Int, planetInfect: Boolean) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j && planet.planetInfect
        }
        if (planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList[index] = Planet(x = i, y = j, planetImage = planet.planetImage, planetInfect = planetInfect,
                satiety = planet.satiety)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun addFood(i: Int, j: Int, foodImage: Drawable, satiety: Int) {
        val food = myFoodList.firstOrNull {food ->
            food.x == i && food.y == j
        }
        if (food == null) {
            myFoodList.add(Food(x = i, y = j, foodImage = foodImage, satiety = satiety))
            spaceListener?.changeSpace(space = this)
        }
    }

    fun planetSatiety(i: Int, j: Int) {
        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
        }
        val food = myFoodList.firstOrNull { food ->
            food.x == i && food.y == j
        }
        if (food != null) {
            val index = myPlanetList.indexOf(planet)
            myFoodList.remove(food)
            myPlanetList[index] = Planet(
                x = planet!!.x,
                y = planet.y,
                planetImage = planet.planetImage,
                planetInfect = planet.planetInfect,
                satiety = food.satiety
            )
            foodAmount -= 1
            spaceListener?.changeSpace(space = this)
        }
    }

    fun planetDecay(i: Int, j: Int, planetImage: Drawable, planetInfect: Boolean, satiety: Int) {
        val planet = myPlanetList.firstOrNull{planet ->
            planet.x == i && planet.y == j
        }
        if (satiety >= 100 && planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList.add(Planet(x = i, y = j, planetImage = planetImage, planetInfect = false, satiety = 0))
            myPlanetList[index] = Planet(x = i, y = j, planetImage = planetImage, planetInfect = planetInfect, satiety = 0)
            spaceListener?.changeSpace(space = this)
        }
    }

    fun planetMovingChange(index: Int, i: Int, j: Int, planetImage: Drawable, planetInfect: Boolean, satiety: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
        }

        if (planet == null) {
            myPlanetList[index] = Planet(x = i, y = j, planetImage = planetImage, planetInfect = planetInfect, satiety = satiety)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun change() {

        spaceListener?.changeSpace(space = this)

    }



}