package com.example.data.objectDao.planet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.space.Space

@Entity (
    tableName = "planets"
)
data class PlanetEntity (
    override var planetX: Int = 0,
    override var planetY: Int = 0,
    override var planetImage: Space.PlanetImage = Space.PlanetImage.PLANET1,
    override var planetInfect: Int = 0,
    override var planetSatiety: Int = 0,
    override var age: Int = 0,
    @ColumnInfo(name = "space_id")
    var spaceId: Long = 0,
): Planet {
    @PrimaryKey(autoGenerate = true)
    var idPlanet: Long = 0

    constructor(spaceId: Long, planet: Planet): this(
        planetX = planet.planetX,
        planetY = planet.planetY,
        planetImage = planet.planetImage,
        planetInfect = planet.planetInfect,
        planetSatiety = planet.planetSatiety,
        age = planet.age,
        spaceId = spaceId
    )

}