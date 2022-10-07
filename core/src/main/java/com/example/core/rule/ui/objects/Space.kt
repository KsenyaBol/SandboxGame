package com.example.core.rule.ui.objects

import android.graphics.Canvas

class Space {

    var size: Int = 0

    var myDeleteRectList: ArrayList<Array<Int>> = arrayListOf()
    var myCellList: ArrayList<Cell> = arrayListOf()

    fun setValue( myDeleteRectList: ArrayList<Array<Int>>, myCellList: ArrayList<Cell>) {
        this.myDeleteRectList = myDeleteRectList
        this.myCellList = myCellList
    }

    fun addValue(i: Int, j: Int, cellColor: Int, cellInfect: Boolean) {
        val cell = myCellList.firstOrNull { cell ->
            cell.x == i && cell.y == j
        }
        if (cell != null) {
            var indexInfect = myCellList.indexOf(Cell(x = i, y = j, cellColor = cellColor, cellInfect = cellInfect))
            myCellList[indexInfect] = Cell(x = i, y = j, cellColor = cellColor, cellInfect = true)
        } else {
            myCellList.add(Cell(x = i, y = j, cellColor = cellColor, cellInfect = cellInfect))
        }

    }

    fun deleteValue(i: Int, j: Int) {
        val cell = myCellList.firstOrNull { cell ->
            cell.x == i && cell.y == j
        }
        myCellList.remove(cell)
        myDeleteRectList.add(arrayOf(i, j))

    }

    fun treatValue(i: Int, j: Int, cellColor: Int, cellInfect: Boolean) {
        val cell = myCellList.firstOrNull { cell ->
            cell.x == i && cell.y == j && cell.cellInfect == true
        }

        if (cell != null) {
            val index = myCellList.indexOf(Cell(x = i, y = j, cellColor = cellColor, cellInfect = cellInfect))
            myCellList[index] = Cell(x = i, y = j, cellColor = cellColor, cellInfect = false)
        }

    }



}