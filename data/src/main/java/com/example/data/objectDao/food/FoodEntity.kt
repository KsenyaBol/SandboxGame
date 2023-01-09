package com.example.data.objectDao.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.core.rule.ui.objects.food.Food
import com.example.core.rule.ui.objects.space.Space

@Entity(
    tableName = "foods"
)
data class FoodEntity (

    var x: Int = 0,
    var y: Int = 0,
    var foodImage: Space.FoodImage = Space.FoodImage.FOOD_M,
    var satiety: Int = 0,
    @ColumnInfo(name = "space_id")
    var spaceId: Int = 0,
    ) {

    @PrimaryKey(autoGenerate = true)
    var idFood: Long = 0


    companion object {
        fun toFood(foodEntity: FoodEntity): Food = Food(
            x = foodEntity.x,
            y = foodEntity.y,
            foodImage = foodEntity.foodImage,
            satiety = foodEntity.satiety,
            spaceId = foodEntity.spaceId,
        )

        fun fromFood(food: Food): FoodEntity = FoodEntity (
            x = food.x,
            y = food.y,
            foodImage = food.foodImage,
            satiety = food.satiety,
            spaceId = food.spaceId,
        )
    }


}