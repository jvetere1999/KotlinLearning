package test.com.jvetere.kotlin.mathtest

import com.jvetere.kotlin.math.Function
import com.jvetere.kotlin.math.Grid
import com.jvetere.kotlin.math.createFunction
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GridTest {

    @Test
    internal fun basic(){
        val func1: Function = createFunction("2x^2+sin(x^2)^2")
        var grid: Grid = Grid(25, 25, func1)
        println(grid)
    }
    @Test
    fun get() {

    }

    @Test
    fun set() {
    }
}