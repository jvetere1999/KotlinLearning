package test.com.jvetere.kotlin

import com.jvetere.kotlin.Grid
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GridTest {

    @Test
    fun basic(){
        val grid: Grid = Grid(50,50, null)
        assertAll("Basic Data",
            { assertEquals(24, grid.logicalMaxX) },
            { assertEquals(24, grid.logicalMaxY) }
        )
    }
    @Test
    fun get() {

    }

    @Test
    fun set() {
    }
}