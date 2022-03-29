package test.com.jvetere.kotlin

import com.jvetere.kotlin.createFunction
import com.jvetere.kotlin.Function
import org.junit.jupiter.api.Test
import kotlin.math.exp

internal class FunctionTest {
    @Test
    internal fun basic() {
        val exp: String = "-2sin(+x^2)+2x+1"
        val func: Function = createFunction(exp)
    }
}