package test.com.jvetere.kotlin.mathtest

import com.jvetere.kotlin.math.Grid
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GridTest {

    @Test
    fun basic(){
        val grid: Grid = Grid(10,10, null)
//        assertAll("Basic Data",
//            { assertEquals(4, grid.logicalMaxX) },
//            { assertEquals(4, grid.logicalMaxY) }
//        )
        grid[0, 0] = true
        println(grid.toString())
    }
    @Test
    fun get() {

    }

    @Test
    fun set() {
    }
}