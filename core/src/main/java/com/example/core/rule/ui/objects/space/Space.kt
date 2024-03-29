package com.example.core.rule.ui.objects.space

import androidx.room.Ignore
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.food.FoodObject
import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.planet.PlanetObject
import java.io.Serializable
import kotlin.collections.ArrayList

class Space(val size: Int): Serializable {

    @Transient
    @Ignore
    var spaceListener: SpaceListener? = null
    var myPlanetList: ArrayList<Planet> = arrayListOf()
    var myFoodList: ArrayList<Food> = arrayListOf()

    @Transient
    @Ignore
    var planetListener: Planet? = null

    @Transient
    @Ignore
    var foodListener: Food? = null

    enum class FoodImage {
        FOOD_XS, FOOD_S, FOOD_M, FOOD_L
    }

    enum class PlanetImage {
        PLANET1, PLANET2, PLANET3, PLANET4, PLANET5,
        PLANET6, PLANET7, PLANET8,PLANET9, PLANET10,
    }

    fun setValue(myPlanetList: ArrayList<Planet>, spaceListener: SpaceListener, myFoodList:
    ArrayList<Food>
    ) {
        this.myPlanetList = myPlanetList
        this.spaceListener = spaceListener
        this.myFoodList = myFoodList
    }

    fun addValue(i: Int, j: Int, planetImage: PlanetImage, planetInfect: Int, satiety: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.planetX == i && planet.planetY == j
        }
        if (planet == null) {
            myPlanetList.add(PlanetObject(planetX = i, planetY = j, planetImage = planetImage, planetInfect = planetInfect,
                planetSatiety = satiety, age = age))
            spaceListener?.changeSpace(space = this)
        }

    }

    fun infectValue(i: Int, j: Int, planetInfect: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.planetX == i && planet.planetY == j
        }
        if (planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList[index] = PlanetObject(planetX = i, planetY = j, planetImage = planet.planetImage, planetInfect = planetInfect,
                planetSatiety = planet.planetSatiety, age = age)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun deleteValue(i: Int, j: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.planetX == i && planet.planetY == j
        }
        myPlanetList.remove(planet)
        spaceListener?.changeSpace(space = this)

    }

    fun treatValue(i: Int, j: Int, planetInfect: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.planetX == i && planet.planetY == j && planet.planetInfect >= 50
        }
        if (planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList[index] = PlanetObject(planetX = i, planetY = j, planetImage = planet.planetImage, planetInfect =
            planetInfect,
                planetSatiety = planet.planetSatiety, age = age)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun addFoodUser(i: Int, j: Int, foodCommand: FoodImage, satiety: Int) {

        val food = myFoodList.firstOrNull {food ->
            food.x == i && food.y == j
        }
        if (food == null) {
            myFoodList.add(FoodObject(x = i, y = j, foodImage = foodCommand , satiety = satiety))
            spaceListener?.changeSpace(space = this)
        }

    }

    fun planetSatiety(i: Int, j: Int, satiety: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            (planet.planetX == i && planet.planetY == j) || (planet.planetX == i - 1 && planet.planetY == j) ||
                    (planet.planetX == i + 1 && planet.planetY == j) || (planet.planetY == j - 1 && planet.planetX == i) ||
                    (planet.planetY == j + 1 && planet.planetX == i)
        }
        if (planet != null) {
            val food = myFoodList.firstOrNull { food ->
                food.x == i && food.y == j
            }
            if (food != null) {

                val index = myPlanetList.indexOf(planet)
                var satietyAll = satiety
                satietyAll += food.satiety
                myFoodList.remove(food)
                myPlanetList[index] = PlanetObject(
                    planetX = planet.planetX,
                    planetY = planet.planetY,
                    planetImage = planet.planetImage,
                    planetInfect = planet.planetInfect,
                    planetSatiety = satietyAll,
                    age = age,
                )
                spaceListener?.changeSpace(space = this)

            }
        }

    }


    fun planetDecay(i: Int, j: Int, planetImage: PlanetImage, planetInfect: Int, satiety: Int, age: Int) {

        val planet = myPlanetList.firstOrNull{planet ->
            (planet.planetX == i && planet.planetY == j) || (planet.planetX == i - 1 && planet.planetY == j) || (planet.planetX
                    == i + 1 && planet.planetY == j) ||
                    (planet.planetY == j - 1 && planet.planetX == i) || (planet.planetY == j + 1 && planet.planetX == i)
        }
        if (satiety >= 100 && planet != null) {

            val index = myPlanetList.indexOf(planet)
            val planetSatiety = satiety - 100
            myPlanetList.add(PlanetObject(planetX = i, planetY = j, planetImage = planetImage, planetInfect = 0, planetSatiety = 0, age = 0))
            myPlanetList[index] = PlanetObject(planetX = i, planetY = j, planetImage = planetImage, planetInfect = planetInfect, planetSatiety =
            planetSatiety, age = age)
            spaceListener?.changeSpace(space = this)

        }
    }

    fun planetMovingChange(i: Int, j: Int, planetImage: PlanetImage, planetInfect: Int, satiety: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            (planet.planetX == i - 1 && planet.planetY == j) || (planet.planetX
                    == i + 1 && planet.planetY == j) ||
                    (planet.planetY == j - 1 && planet.planetX == i) || (planet.planetY == j + 1 && planet.planetX == i)
        }

        if (planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList[index] = PlanetObject(planetX = i, planetY = j, planetImage = planetImage, planetInfect = planetInfect, planetSatiety =
            satiety, age = age)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun foodChange(i: Int, j: Int, satiety: Int) {

        val food = myFoodList.firstOrNull { food ->
            food.x == i && food.y == j
        }
        if (food == null) {
            myFoodList.add(FoodObject(x = i, y = j, foodImage = FoodImage.FOOD_M , satiety = satiety))
            spaceListener?.changeSpace(space = this)
        }

    }

    fun ageChange(index: Int, i: Int, j: Int, age: Int) {
        val planet = myPlanetList.firstOrNull {planet ->
            planet.planetX == i && planet.planetY == j
        }
        if (planet != null) {
            myPlanetList[index] = PlanetObject(planetX = i, planetY = j, planetImage = planet.planetImage, planetInfect = planet.planetInfect,
                planetSatiety = planet.planetSatiety, age = age)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun infectChange(index: Int, i: Int, j: Int, planetInfect: Int) {
        val planet = myPlanetList.firstOrNull {planet ->
            planet.planetX == i && planet.planetY == j
        }
        if (planet != null) {
            myPlanetList[index] = PlanetObject(planetX = i, planetY = j, planetImage = planet.planetImage, planetInfect = planetInfect,
                planetSatiety = planet.planetSatiety, age = planet.age)
            spaceListener?.changeSpace(space = this)
        }
    }

    fun reviewPlanet(iPlanet: Int, jPlanet: Int, iFood: Int, jFood: Int) {

        val planet = myPlanetList.firstOrNull{ planet ->
            planet.planetX == iPlanet && planet.planetY == jPlanet
        }
        if (planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList[index] = PlanetObject(iFood, jFood, planet.planetImage, planet.planetInfect, planet.planetSatiety, planet.age)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun planetDie(i: Int, j: Int, age: Int) {
        ArrayList(myPlanetList).forEachIndexed { index, planet ->
            if (planet.planetX == i && planet.planetY == j && age >= 100) {
                myPlanetList.removeAt(index)
                spaceListener?.changeSpace(space = this)
            }

        }
    }



}