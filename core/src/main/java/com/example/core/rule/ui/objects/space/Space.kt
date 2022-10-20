package com.example.core.rule.ui.objects.space

import android.graphics.drawable.Drawable
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.planet.Planet
import kotlin.collections.ArrayList

class Space {

    var myPlanetList: ArrayList<Planet> = arrayListOf()
    var myFoodList: ArrayList<Food> = arrayListOf()
    var spaceListener: SpaceListener? = null

    fun setValue(myPlanetList: ArrayList<Planet>, spaceListener: SpaceListener, myFoodList: ArrayList<Food>) {

        this.myPlanetList = myPlanetList
        this.spaceListener = spaceListener
        this.myFoodList = myFoodList

    }

    fun addValue(i: Int, j: Int, planetImage: Drawable, planetInfect: Int, satiety: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
        }
        if (planet == null) {
            myPlanetList.add(Planet(x = i, y = j, planetImage = planetImage, planetInfect = planetInfect, satiety = satiety,
                age = age))
            spaceListener?.changeSpace(space = this)
        }

    }

    fun infectValue(i: Int, j: Int, planetInfect: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
        }
        if (planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList[index] = Planet(x = i, y = j, planetImage = planet.planetImage, planetInfect = planetInfect,
                satiety = planet.satiety, age = age)
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

    fun treatValue(i: Int, j: Int, planetInfect: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j && planet.planetInfect >= 50
        }
        if (planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList[index] = Planet(x = i, y = j, planetImage = planet.planetImage, planetInfect = planetInfect,
                satiety = planet.satiety, age = age)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun addFoodUser(i: Int, j: Int, foodImage: Drawable, satiety: Int) {

        val food = myFoodList.firstOrNull {food ->
            food.x == i && food.y == j
        }
        if (food == null) {
            myFoodList.add(Food(x = i, y = j, foodImage = foodImage, satiety = satiety))
            spaceListener?.changeSpace(space = this)
        }

    }

    fun planetSatiety(i: Int, j: Int, satiety: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
        }
        val food = myFoodList.firstOrNull { food ->
            food.x == i && food.y == j
        }
        if (food != null) {

            val index = myPlanetList.indexOf(planet)
            var satietyAll = satiety
            satietyAll += food.satiety
            myFoodList.remove(food)
            myPlanetList[index] = Planet(
                x = planet!!.x,
                y = planet.y,
                planetImage = planet.planetImage,
                planetInfect = planet.planetInfect,
                satiety = satietyAll,
                age = age
            )
            spaceListener?.changeSpace(space = this)

        }
    }


    fun planetDecay(i: Int, j: Int, planetImage: Drawable, planetInfect: Int, satiety: Int, age: Int) {

        val planet = myPlanetList.firstOrNull{planet ->
            planet.x == i && planet.y == j
        }
        if (satiety >= 100 && planet != null) {

            val index = myPlanetList.indexOf(planet)
            val planetSatiety = satiety - 100
            myPlanetList.add(Planet(x = i, y = j, planetImage = planetImage, planetInfect = planetInfect, satiety = 0, age = age))
            myPlanetList[index] = Planet(x = i, y = j, planetImage = planetImage, planetInfect = planetInfect, satiety =
            planetSatiety, age = age)
            spaceListener?.changeSpace(space = this)

        }
    }

    fun planetMovingChange(index: Int, i: Int, j: Int, planetImage: Drawable, planetInfect: Int, satiety: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
        }

        if (planet == null) {
            myPlanetList[index] = Planet(x = i, y = j, planetImage = planetImage, planetInfect = planetInfect, satiety =
            satiety, age = age)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun foodChange(i: Int, j: Int, foodImage: Drawable, satiety: Int) {

        val food = myFoodList.firstOrNull { food ->
            food.x == i && food.y == j
        }
        if (food == null) {
            myFoodList.add(Food(x = i, y = j, foodImage = foodImage , satiety = satiety))
            spaceListener?.changeSpace(space = this)
        }

    }

    fun ageChange(index: Int, i: Int, j: Int, age: Int) {
        val planet = myPlanetList.firstOrNull {planet ->
            planet.x == i && planet.y == j
        }
        if (planet != null) {
            myPlanetList[index] = Planet(x = i, y = j, planetImage = planet.planetImage, planetInfect = planet.planetInfect,
                satiety = planet.satiety, age = age)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun infectChange(index: Int, i: Int, j: Int, planetInfect: Int) {
        val planet = myPlanetList.firstOrNull {planet ->
            planet.x == i && planet.y == j
        }
        if (planet != null) {
            myPlanetList[index] = Planet(x = i, y = j, planetImage = planet.planetImage, planetInfect = planetInfect,
                satiety = planet.satiety, age = planet.age)
            spaceListener?.changeSpace(space = this)
        }
    }

    fun planetDie() {
        myPlanetList.forEachIndexed { index, planet ->
            if (planet.age >= 100) {
                myPlanetList.removeAt(index)
                spaceListener?.changeSpace(space = this)
            }
        }
    }



}