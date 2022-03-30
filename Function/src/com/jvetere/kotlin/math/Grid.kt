package com.jvetere.kotlin.math

import java.util.stream.IntStream.range

data class Grid( val xMax: Int, val yMax: Int, var function: Function?) {
    //TODO Back to private user doesnt need to see
    var grid = Array(yMax) { BooleanArray(xMax) }
    val logicalMaxX: Int = (xMax / 2) - 1 //If bounds are 50 24
    val logicalMaxY: Int = (yMax / 2) - 1 //If bounds are 50 24
    init {
        for(x in range(0, xMax)) {
            when (function){
                null -> {for(y in range(0, yMax)) { grid[y][x] = false }}
                else -> {
                    val input: Int = x-logicalMaxX
                    val y : Double? = function?.let { it(input) }
                    if (y != null && y > -logicalMaxY && y < logicalMaxY ){ set(y.toInt() , input, true) }
                }
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
        var str: StringBuilder = StringBuilder()
        val bar: StringBuilder = StringBuilder()
        for (y in range(0, xMax)){
            bar.append("---")
        }
        for(x in (yMax-1).downTo(0)) {
            for (y in range(0, xMax)) {
                if (grid[y][x])
                    str.append("|*|")
                else
                    str.append("| |")

            }
            str.append("\n")
            str.append(bar)
            str.append("\n")
        }
        return str.toString()
    }
}