package com.example.data.objectDao.space

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.core.rule.ui.objects.space.SpaceObject

@Entity(
    tableName = "space"
)
data class SpaceEntity (

    var size: Int = 0,

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        fun toSpace(spaceEntity: SpaceEntity): SpaceObject = SpaceObject(
            size = spaceEntity.size
        )

        fun fromPlanet(spaceObject: SpaceObject): SpaceEntity = SpaceEntity (
            size = spaceObject.size
        )
    }

}