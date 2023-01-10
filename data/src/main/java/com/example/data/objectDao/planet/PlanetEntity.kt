package com.example.data.objectDao.planet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.space.Space

@Entity (
    tableName = "planets"
)
data class PlanetEntity (

    var planetX: Int = 0,
    var planetY: Int = 0,
    var planetImage: Space.PlanetImage = Space.PlanetImage.PLANET1,
    var planetInfect: Int = 0,
    var planetSatiety: Int = 0,
    var age: Int = 0,
    @ColumnInfo(name = "space_id")
    var spaceId: Int = 0,
) {
    @PrimaryKey(autoGenerate = true)
    var idPlanet: Long = 0

    companion object {
        fun toPlanet(planetEntity: PlanetEntity): Planet = Planet(
            planetX = planetEntity.planetX,
            planetY = planetEntity.planetY,
            planetImage = planetEntity.planetImage,
            planetInfect = planetEntity.planetInfect,
            planetSatiety = planetEntity.planetSatiety,
            age = planetEntity.age,
            spaceId = planetEntity.spaceId,
        )

        fun fromPlanet(planet: Planet): PlanetEntity = PlanetEntity (
            planetX = planet.planetX,
            planetY = planet.planetY,
            planetImage = planet.planetImage,
            planetInfect = planet.planetInfect,
            planetSatiety = planet.planetSatiety,
            age = planet.age,
            spaceId = planet.spaceId,
        )
    }
}