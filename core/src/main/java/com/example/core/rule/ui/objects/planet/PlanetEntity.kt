package com.example.core.rule.ui.objects.planet
//
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import com.example.core.rule.ui.objects.space.Space
//
//@Entity (
//    tableName = "planet"
//        )
//data class PlanetEntity (
//    var planetX: Int = 0,
//    var planetY: Int = 0,
//    var planetImage: Space.PlanetImage = Space.PlanetImage.PLANET1,
//    var planetInfect: Int = 0,
//    var planetSatiety: Int = 0,
//    var age: Int = 0,
//    @PrimaryKey @ColumnInfo(name = "spaceId") val spaceId: Int = 0,
//) {
//    fun toPlanet(): Planet = Planet(
//        planetX = planetX,
//        planetY = planetY,
//        planetImage = planetImage,
//        planetInfect = planetInfect,
//        planetSatiety = planetSatiety,
//        age = age,
//        spaceId = spaceId,
//    )
//
//    companion object {
//        fun fromPlanet(planet: Planet): PlanetEntity = PlanetEntity (
//            planetX = planet.planetX,
//            planetY = planet.planetY,
//            planetImage = planet.planetImage,
//            planetInfect = planet.planetInfect,
//            planetSatiety = planet.planetSatiety,
//            age = planet.age,
//            spaceId = planet.spaceId,
//        )
//    }
//}