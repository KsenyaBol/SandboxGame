package com.example.core.rule.ui.objects.food

import android.graphics.drawable.Drawable
import com.example.core.rule.ui.objects.space.SpaceListener

class FoodOnSpace {

    var myFoodList: ArrayList<Food> = arrayListOf()
//    var foodListener: SpaceListener? = null

    fun setValue(myFoodList: ArrayList<Food>, foodListener: SpaceListener) {
        this.myFoodList = myFoodList
//        this.foodListener = foodListener
    }

    fun addFood(i: Int, j: Int, foodImage: Drawable, saturation: Int) {
        val food = myFoodList.firstOrNull {food ->
            food.x == i && food.y == j
        }
        if (food == null) {
            myFoodList.add(Food(x = i, y = j, foodImage = foodImage, saturation = saturation))
//            foodListener?.changeFoodOnSpace(food = this)
        }
    }

}