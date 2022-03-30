package com.jvetere.kotlin.math

data class Graph(val yBound: Int, val xBound: Int, val grids: List<Grid>?, val functions: List<Function>?) {
    //TODO figure out 3d data structure and set it equal to an iteration over the list of functions adding their grid

    operator fun get (y: Int, x: Int, z: Int): Int {
        TODO("Call to 3D array")
    }

    fun collapse(): Grid {
        return TODO("Check go down check go down true bounce up set that space on new graph to true move on and just over and over")
    }
}
