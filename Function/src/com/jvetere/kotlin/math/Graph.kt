package com.jvetere.kotlin.math

import java.util.stream.IntStream.range

data class Graph(val yBound: Int, val xBound: Int, val functions: List<Function>) {
    private var grids = Array(functions.size) { Grid(yBound, xBound, null) }
    init {
        for( x in range(0, xBound)) {
            for (funcNum in range(0, functions.size)) {
                val y: Double? = functions[funcNum](x)
                if (y != null && y < yBound)
                    grids[funcNum][y.toInt(), x] = true
            }
        }
    }
    operator fun get (z: Int, y: Int, x: Int): Boolean { //[z, y, x]
        return grids[z][y, x]
    }
    operator fun get(z: Int): Grid {
        return grids[z]
    }

    fun collapse(): Grid {
        var flatGraph: Grid = Grid(yBound, xBound, null)
        for (y in range(0, yBound)) {
            for (x in range(0, xBound)) {
                for (z in range(0, functions.size)) {
                    if (grids[z][y, x]) {
                        flatGraph[y, x] = true
                        break;
                    }
                }
            }
        }
        return flatGraph
    }

    override fun toString(): String {
        var sb: StringBuilder = StringBuilder();
        for (grid in grids){
            sb.append("$grid\n")
        }
        return sb.toString()
    }
}
