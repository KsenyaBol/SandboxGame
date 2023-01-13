package com.example.data.objectDao.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.space.Space

@Entity(
    tableName = "foods"
)
data class FoodEntity (

    override var x: Int = 0,
    override var y: Int = 0,
    override var foodImage: Space.FoodImage = Space.FoodImage.FOOD_M,
    override var satiety: Int = 0,
    @ColumnInfo(name = "space_id")
    var spaceId: Long = 0,
    ): Food {

    @PrimaryKey(autoGenerate = true)
    var idFood: Long = 0

    constructor(spaceId: Long, food: Food): this (

        x = food.x,
        y =food.y,
        foodImage = food.foodImage,
        satiety = food.satiety,
        spaceId = spaceId

    )

//    override fun foodObg(x: Int, y: Int, foodImage: Space.FoodImage, satiety: Int) {
//        this.x = x
//        this.y = y
//        this.foodImage = foodImage
//        this.satiety = satiety
//    }

}
