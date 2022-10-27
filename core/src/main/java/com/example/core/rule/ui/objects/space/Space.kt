package com.example.core.rule.ui.objects.space

import android.graphics.drawable.Drawable
import com.example.core.R
import com.example.core.rule.ui.objects.food.ConvertFoodImage
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.planet.ConvertPlanetImage
import com.example.core.rule.ui.objects.planet.Planet
import kotlin.collections.ArrayList

class Space {

    var myPlanetList: ArrayList<Planet> = arrayListOf()
    var myPlanetList2: ArrayList<Planet> = arrayListOf()
    var myFoodList: ArrayList<Food> = arrayListOf()
    var spaceListener: SpaceListener? = null
    var convertPlanetImage: ConvertPlanetImage = ConvertPlanetImage()
    var convertFoodImage: ConvertFoodImage = ConvertFoodImage()




    fun setValue(myPlanetList: ArrayList<Planet>, myPlanetList2: ArrayList<Planet>, spaceListener: SpaceListener, myFoodList:
    ArrayList<Food>) {

        this.myPlanetList = myPlanetList
        this.myPlanetList2 = myPlanetList2
        this.spaceListener = spaceListener
        this.myFoodList = myFoodList
    }

    fun addValue(i: Int, j: Int, planetImage: Drawable, planetInfect: Int, satiety: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
        }
        if (planet == null) {
            myPlanetList.add(Planet(x = i, y = j, planetImage = convertPlanetImage.planetString, planetInfect =
            planetInfect,
                satiety =
            satiety,
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
            myFoodList.add(Food(x = i, y = j, foodImage = convertFoodImage.foodString , satiety = satiety))
            spaceListener?.changeSpace(space = this)
        }

    }

    fun planetSatiety(i: Int, j: Int, satiety: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
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
                myPlanetList[index] = Planet(
                    x = planet.x,
                    y = planet.y,
                    planetImage = planet.planetImage,
                    planetInfect = planet.planetInfect,
                    satiety = satietyAll,
                    age = age
                )
                spaceListener?.changeSpace(space = this)

            }
        }

    }


    fun planetDecay(i: Int, j: Int, planetImage: ConvertPlanetImage.CommandImage, planetInfect: Int, satiety: Int, age: Int) {

        val planet = myPlanetList.firstOrNull{planet ->
            planet.x == i && planet.y == j
        }
        if (satiety >= 100 && planet != null) {

            val index = myPlanetList.indexOf(planet)
            val planetSatiety = satiety - 100
            myPlanetList.add(Planet(x = i, y = j, planetImage = convertPlanetImage.planetString, planetInfect = 0, satiety = 0, age = 0))
            myPlanetList[index] = Planet(x = i, y = j, planetImage = convertPlanetImage.planetString, planetInfect = planetInfect, satiety =
            planetSatiety, age = age)
            spaceListener?.changeSpace(space = this)

        }
    }

    fun planetMovingChange( i: Int, j: Int, planetImage: ConvertPlanetImage.CommandImage, planetInfect: Int, satiety: Int, age: Int) {

        val planet = myPlanetList.firstOrNull { planet ->
            (planet.x == i - 1 && planet.y == j) || (planet.x == i + 1 && planet.y == j) ||
                    (planet.y == j - 1 && planet.x == i) || (planet.y == j + 1 && planet.x == i)
        }

        if (planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList[index] = Planet(x = i, y = j, planetImage = convertPlanetImage.planetString, planetInfect = planetInfect, satiety =
            satiety, age = age)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun foodChange(i: Int, j: Int, foodImage: Drawable, satiety: Int) {

        val food = myFoodList.firstOrNull { food ->
            food.x == i && food.y == j
        }
        if (food == null) {
            myFoodList.add(Food(x = i, y = j, foodImage = convertFoodImage.foodString , satiety = satiety))
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

    fun reviewPlanet(iPlanet: Int, jPlanet: Int, iFood: Int, jFood: Int) {

        val planet = myPlanetList.firstOrNull{ planet ->
            planet.x == iPlanet && planet.y == jPlanet
        }
        if (planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList[index] = Planet(iFood, jFood, planet.planetImage, planet.planetInfect, planet.satiety, planet.age)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun planetDie(i: Int, j: Int, age: Int) {
        ArrayList(myPlanetList).forEachIndexed { index, planet ->
            if (planet.x == i && planet.y == j && age >= 100) {
                myPlanetList.removeAt(index)
                spaceListener?.changeSpace(space = this)
            }

        }
    }





}