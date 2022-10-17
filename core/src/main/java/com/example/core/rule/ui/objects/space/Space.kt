package com.example.core.rule.ui.objects.space

import android.graphics.drawable.Drawable
import com.example.core.rule.ui.objects.planet.Planet

class Space {

    var myPlanetList: ArrayList<Planet> = arrayListOf()
    var spaceListener: SpaceListener? = null

    fun setValue(myPlanetList: ArrayList<Planet>, spaceListener: SpaceListener) {

        this.myPlanetList = myPlanetList
        this.spaceListener = spaceListener

    }

    fun addValue(i: Int, j: Int, planetImage: Drawable, planetInfect: Boolean) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
        }
        if (planet == null) {
            myPlanetList.add(Planet(x = i, y = j, planetImage = planetImage, planetInfect = planetInfect))
            spaceListener?.changeSpace(space = this)
        }

    }

    fun infectValue(i: Int, j: Int, planetInfect: Boolean) {

        val planet = myPlanetList.firstOrNull { planet ->
            planet.x == i && planet.y == j
        }
        if (planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList[index] = Planet(x = i, y = j, planetImage = planet.planetImage, planetInfect = planetInfect)
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
            planet.x == i && planet.y == j && planet.planetInfect == true
        }
        if (planet != null) {
            val index = myPlanetList.indexOf(planet)
            myPlanetList[index] = Planet(x = i, y = j, planetImage = planet.planetImage, planetInfect = planetInfect)
            spaceListener?.changeSpace(space = this)
        }

    }

//    fun planetMovingChange(index: Int, i: Int, j: Int, planetImage: Drawable, planetInfect: Boolean) {
//
//        val planet = myPlanetList.firstOrNull { planet ->
//            planet.x == i && planet.y == j
//        }
//
//        if (planet == null) {
//            myPlanetList[index] = Planet(x = i, y = j, planetImage = planetImage, planetInfect = planetInfect)
//            spaceListener?.changeSpace(space = this)
//        }
//
//    }
//
//    fun change() {
//
//        spaceListener?.changeSpace(space = this)
//
//    }



}