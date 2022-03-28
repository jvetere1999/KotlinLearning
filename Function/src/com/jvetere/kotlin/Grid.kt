package com.jvetere.kotlin

import java.util.stream.IntStream.range

data class Grid( val xMax: Int, val yMax: Int, var function: Function?) {
    //TODO Back to private user doesnt need to see
    var grid = Array(yMax) { BooleanArray(xMax) }
    val logicalMaxX: Int = (xMax / 2) - 1 //If bounds are 50 24
    val logicalMaxY: Int = (yMax / 2) - 1 //If bounds are 50 24
    init {
//TODO
//        for(x in range(0,xMax)) {
//            val y: Int? = function[x]
//            if (y != null && y < logicalMaxY ){
//                grid[y][x] = true;
//            }
//        }
    }
    operator fun get(y: Int, x: Int) : Boolean {//if y 24 x-24 really 48 and 0
        return grid[y+logicalMaxY][x+logicalMaxX]
    }
    operator fun set(y: Int, x: Int, space: Boolean){
        grid[y+logicalMaxY][x+logicalMaxX] = space
    }
}