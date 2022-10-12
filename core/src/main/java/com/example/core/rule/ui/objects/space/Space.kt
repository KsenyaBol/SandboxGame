package com.example.core.rule.ui.objects.space

import com.example.core.rule.ui.objects.Cell

class Space {

    var myCellList: ArrayList<Cell> = arrayListOf()
    var spaceListener: SpaceListener? = null

    fun setValue(myCellList: ArrayList<Cell>, spaceListener: SpaceListener) {

        this.myCellList = myCellList
        this.spaceListener = spaceListener

    }

    fun addValue(i: Int, j: Int, cellColor: Int, cellInfect: Boolean) {

        val cell = myCellList.firstOrNull { cell ->
            cell.x == i && cell.y == j
        }
        if (cell == null) {
            myCellList.add(Cell(x = i, y = j, cellColor = cellColor, cellInfect = cellInfect))
            spaceListener?.changeSpace(space = this)
        }

    }

    fun infectValue(i: Int, j: Int, cellInfect: Boolean) {

        val cell = myCellList.firstOrNull { cell ->
            cell.x == i && cell.y == j
        }
        if (cell != null) {
            val index = myCellList.indexOf(cell)
            myCellList[index] = Cell(x = i, y = j, cellColor = cell.cellColor, cellInfect = cellInfect)
            spaceListener?.changeSpace(space = this)
        }

    }

    fun deleteValue(i: Int, j: Int) {

        val cell = myCellList.firstOrNull { cell ->
            cell.x == i && cell.y == j
        }
        myCellList.remove(cell)
        spaceListener?.changeSpace(space = this)

    }

    fun treatValue(i: Int, j: Int, cellInfect: Boolean) {

        val cell = myCellList.firstOrNull { cell ->
            cell.x == i && cell.y == j && cell.cellInfect == true
        }
        if (cell != null) {
            val index = myCellList.indexOf(cell)
            myCellList[index] = Cell(x = i, y = j, cellColor = cell.cellColor, cellInfect = cellInfect)
            spaceListener?.changeSpace(space = this)
        }

    }



}