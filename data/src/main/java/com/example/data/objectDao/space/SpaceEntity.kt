package com.example.data.objectDao.space

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.rule.ui.objects.space.Space

@Entity(
    tableName = "space"
)
data class SpaceEntity (
    var size: Int = 0,
) {

    constructor(space: Space): this(space.size)

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}