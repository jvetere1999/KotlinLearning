package test.com.jvetere.kotlin.mathtest

import com.jvetere.kotlin.flags.OuterFunctionFlag
import com.jvetere.kotlin.math.Term
import com.jvetere.kotlin.math.createTerm
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

internal class TermKtTest {

    @Test
    internal fun varIntAndEx() {
        val term: Term = createTerm("2x^2")
        assertAll("Basic",
            { assertEquals(false,   term.neg) },
            { assertEquals(2,       term.const) },
            { assertEquals(2,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(2.0,       term.calculate(1.0)) }
        )
        val firstDerivation: Term? = term.derive()
        if (firstDerivation != null) {
            assertAll("Derive",
                { assertEquals(false,   firstDerivation.neg) },
                { assertEquals(4,       firstDerivation.const) },
                { assertEquals(1,       firstDerivation.exponent) },
                { assertEquals("x",     firstDerivation.variable) },
                { assertEquals(4.0,       firstDerivation.calculate(1.0)) }
            )
        }
    }
    @Test
    internal fun varIntAndExNeg() {
        val term: Term = createTerm("-2(x^2)");
        assertAll("Basic",
            { assertEquals(true,    term.neg) },
            { assertEquals(2,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("x^2",     term.variable) },
            { assertEquals(-8.0,      term.calculate(2.0)) }
        )
        val firstDerivation: Term? = term.derive()
        if (firstDerivation != null) {
            assertAll("Derive",
                { assertEquals(true,   firstDerivation.neg) },
                { assertEquals(4,       firstDerivation.const) },
                { assertEquals(1,       firstDerivation.exponent) },
                { assertEquals("x",     firstDerivation.variable) },
                { assertEquals(-4.0,       firstDerivation.calculate(1.0)) }
            )
        }
    }
    @Test
    internal fun varAndInt() {
        val term: Term = createTerm("+2x");
        assertAll("Basic",
            { assertEquals(false,   term.neg) },
            { assertEquals(2,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(8.0,       term.calculate(4.0)) }
        )
        val firstDerivation: Term? = term.derive()
        if (firstDerivation != null) {
            assertAll("Derive",
                { assertEquals(false,   firstDerivation.neg) },
                { assertEquals(2,       firstDerivation.const) },
                { assertEquals(0,       firstDerivation.exponent) },
                { assertEquals("x",     firstDerivation.variable) },
                { assertEquals(2.0,       firstDerivation.calculate(1.0)) }
            )
        }
    }
    @Test
    internal fun singleIntTerm() {
        val term: Term = createTerm("2");
        assertAll("Single Integer",
            { assertEquals(false,   term.neg) },
            { assertEquals(2,       term.const) },
            { assertEquals(0,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(2.0,       term.calculate(4.0)) },
            { assertEquals(OuterFunctionFlag.NA, term.flag)}
        )
        val firstDerivation: Term? = term.derive()
        assertAll("Derive",
            { assertEquals(null,   firstDerivation) }
        )
    }


}

