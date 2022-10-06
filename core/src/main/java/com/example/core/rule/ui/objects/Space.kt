package com.example.core.rule.ui.objects

import android.content.Context
import android.util.AttributeSet
import android.view.View

class Space {

    var myDeleteRectList: ArrayList<Array<Int>> = arrayListOf()
    var myCellList: ArrayList<Cell> = arrayListOf()
    var myCellInfectList: ArrayList<Array<Int>> = arrayListOf()

    fun setValue( myDeleteRectList: ArrayList<Array<Int>>, myCellInfectList: ArrayList<Array<Int>>) {
        this.myDeleteRectList = myDeleteRectList
        this.myCellInfectList = myCellInfectList
    }

    fun setValue(myCellList: ArrayList<Cell>) {
        this.myCellList = myCellList
    }

    fun addValue(i: Int, j: Int, cellColor: Int, cellInfect: Boolean) {
        myCellList.add(Cell(x = i, y = j, cellColor = cellColor, cellInfect = cellInfect))

    }

    fun addValue(cell: Cell) {
        val last = myCellList.lastIndex
        myCellList[last] = cell
    }
    fun deleteValue(i: Int, j: Int) {
        val cell = myCellList.firstOrNull { cell ->
            cell.x == i && cell.y == j
        }
        myCellList.remove(cell)
        myDeleteRectList.add(arrayOf(i, j))

        val cellInfect = myCellInfectList.firstOrNull { cell ->
            cell[0] == i && cell[1] == j
        }
        myCellInfectList.remove(cellInfect)

    }

    fun infectValue(i: Int, j: Int) {
        myCellInfectList.add(arrayOf(i, j))
    }
}