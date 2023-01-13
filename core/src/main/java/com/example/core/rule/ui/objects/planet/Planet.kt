package com.example.core.rule.ui.objects.planet

import androidx.room.Ignore
import com.example.core.rule.ui.objects.space.Space
import java.io.Serializable

interface Planet: Serializable {

    var planetX: Int
    var planetY: Int
    var planetImage: Space.PlanetImage
    var planetInfect: Int
    var planetSatiety: Int
    var age: Int

//    fun planetObj(
//        planetX: Int,
//        planetY: Int,
//        planetImage: Space.PlanetImage,
//        planetInfect: Int,
//        planetSatiety: Int,
//        age: Int
//    ): Planet

}