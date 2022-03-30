package com.jvetere.kotlin.math

import java.util.stream.IntStream.range

data class Grid( val xMax: Int, val yMax: Int, var function: Function?) {
    //TODO Back to private user doesnt need to see
    private var grid = Array(yMax) { BooleanArray(xMax) }
    private val logicalMaxX: Int = (xMax / 2) - 1 //If bounds are 50 24
    private val logicalMaxY: Int = (yMax / 2) - 1 //If bounds are 50 24
    init {
        for(x in range(0, yMax)) {
//            for(y in range(0, xMax)) {
//                grid[y][x] = false
//            }
            val y: Double? = function?.let { it(x) }
            if (y != null && y < logicalMaxY ){
                grid[y.toInt()][x] = true;
            }
        }
    }
    operator fun get(y: Int, x: Int) : Boolean {//if y 24 x-24 really 48 and 0
        return grid[y+logicalMaxY][x+logicalMaxX]
    }
    operator fun set(y: Int, x: Int, space: Boolean){
        grid[y+logicalMaxY][x+logicalMaxX] = space
    }

    override fun toString(): String {
        var str: String = ""
        for(x in range(0, yMax)) {
            for (y in range(0, xMax)) {
                val temp = grid[y][x]
                str += "\t$temp"
            }
            str += "\n"
        }
        return str
    }
}