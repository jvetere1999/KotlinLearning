package test.com.jvetere.kotlin.mathtest

import com.jvetere.kotlin.math.Function
import com.jvetere.kotlin.math.createFunction
import org.junit.jupiter.api.Test
import kotlin.math.exp

internal class FunctionTest {
    @Test
    internal fun basic() {
        val exp: String = "-2sin(x^2)+2x+1"
        val func: Function = createFunction(exp)

    }
}