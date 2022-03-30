package test.com.jvetere.kotlin.mathtest

import com.jvetere.kotlin.flags.OuterFunctionFlag
import com.jvetere.kotlin.flags.assignFlag
import com.jvetere.kotlin.math.*
import com.jvetere.kotlin.math.Function
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class TestingRange {

    @Test
    internal fun toStringTesting() {
        val exp: String = "-2sin(x^2)+2x+1"
        val func: Function = createFunction(exp)
        val grid: Grid = Grid(25, 25, func)
        println(grid)
    }
    /**
     * Proposed function test
     * 2x^2+2x+3
     * -2sin(x^2)+2x+1
     */



    /**
     * Term toStringTesting block
     * val exp: String = "x^2"
     * val term: Term = createTerm(exp)
     * val firstOrder: Term? = term.derive()
     * println(term)
     * println(term.calculate(3.0))
     * println(firstOrder)
     * println(firstOrder!!.calculate(3.0))
     */

}