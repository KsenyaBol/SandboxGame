package com.example.data.objectDao.space

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.core.rule.ui.objects.space.SpaceObject

@Entity(
    tableName = "space"
)
data class SpaceEntity (

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var size: Int = 0,

) {

    companion object {
        fun toSpace(spaceEntity: SpaceEntity): SpaceObject = SpaceObject(
            id = spaceEntity.id,
            size = spaceEntity.size
        )

        fun fromPlanet(spaceObject: SpaceObject): SpaceEntity = SpaceEntity (
            id = spaceObject.id,
            size = spaceObject.size
        )
    }

}