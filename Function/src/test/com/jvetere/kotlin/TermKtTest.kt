package test.com.jvetere.kotlin

import com.jvetere.kotlin.OuterFunctionFlag
import com.jvetere.kotlin.Term
import com.jvetere.kotlin.createTerm
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

internal class TermKtTest {

    @Test
    internal fun varIntAndEx() {
        val term: Term = createTerm("2x^2");
        assertAll("Basic",
            { assertEquals(false,   term.neg) },
            { assertEquals(2,       term.const) },
            { assertEquals(2,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(2,       term.calculate(1)) }
        )
        val firstDerivation: Term? = term.derive()
        if (firstDerivation != null) {
            assertAll("Derive",
                { assertEquals(false,   firstDerivation.neg) },
                { assertEquals(4,       firstDerivation.const) },
                { assertEquals(1,       firstDerivation.exponent) },
                { assertEquals("x",     firstDerivation.variable) },
                { assertEquals(4,       firstDerivation.calculate(1)) }
            )
        }
    }
    @Test
    internal fun varIntAndExNeg() {
        val term: Term = createTerm("-2x^2");
        assertAll("Basic",
            { assertEquals(true,    term.neg) },
            { assertEquals(2,       term.const) },
            { assertEquals(2,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(-8,      term.calculate(2)) }
        )
        val firstDerivation: Term? = term.derive()
        if (firstDerivation != null) {
            assertAll("Derive",
                { assertEquals(true,   firstDerivation.neg) },
                { assertEquals(4,       firstDerivation.const) },
                { assertEquals(1,       firstDerivation.exponent) },
                { assertEquals("x",     firstDerivation.variable) },
                { assertEquals(-4,       firstDerivation.calculate(1)) }
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
            { assertEquals(8,       term.calculate(4)) }
        )
        val firstDerivation: Term? = term.derive()
        if (firstDerivation != null) {
            assertAll("Derive",
                { assertEquals(false,   firstDerivation.neg) },
                { assertEquals(2,       firstDerivation.const) },
                { assertEquals(0,       firstDerivation.exponent) },
                { assertEquals("x",     firstDerivation.variable) },
                { assertEquals(2,       firstDerivation.calculate(1)) }
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
            { assertEquals(2,       term.calculate(4)) },
            { assertEquals(OuterFunctionFlag.NA, term.flag)}
        )
        val firstDerivation: Term? = term.derive()
        assertAll("Derive",
            { assertEquals(null,   firstDerivation) }
        )
    }
    @Test
    internal fun lnTerm() {
        val term: Term = createTerm("ln2x");
        assertAll("LN",
            { assertEquals(false,   term.neg) },
            { assertEquals(2,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(8,       term.calculate(4)) },
            { assertEquals(OuterFunctionFlag.LN, term.flag)}
        )
    }
    @Test
    internal fun lnTermFringeNeg() {
        val term: Term = createTerm("-ln2x^2");
        assertAll("neg LN",
            { assertEquals(true,   term.neg) },
            { assertEquals(2,       term.const) },
            { assertEquals(2,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(8,       term.calculate(4)) },
            { assertEquals(OuterFunctionFlag.LN, term.flag)}
        )
    }
    @Test
    internal fun lnTermFringeNegEx() {
        val term: Term = createTerm("-ln2-x^2");
        assertAll("neg EX LN",
            { assertEquals(true,   term.neg) },
            { assertEquals(2,       term.const) },
            { assertEquals(2,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(8,       term.calculate(4)) },
            { assertEquals(OuterFunctionFlag.LN, term.flag)},
            { assertEquals(true, term.negEx)}
        )
    }
    @Test
    internal fun lnTermFringeNegExponet() {
        val term: Term = createTerm("-ln2x^-2");
        assertAll("neg EX LN",
            { assertEquals(true,   term.neg) },
            { assertEquals(2,       term.const) },
            { assertEquals(-2,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(8,       term.calculate(4)) },
            { assertEquals(OuterFunctionFlag.LN, term.flag)},
            { assertEquals(false, term.negEx)}
        )
    }
    @Test
    internal fun lnTermFringeNegConst() {
        val term: Term = createTerm("ln-2x^2");
        assertAll("neg EX LN",
            { assertEquals(false,   term.neg) },
            { assertEquals(-2,       term.const) },
            { assertEquals(2,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(8,       term.calculate(4)) },
            { assertEquals(OuterFunctionFlag.LN, term.flag)},
            { assertEquals(false, term.negEx)}
        )
    }
    @Test
    internal fun lnTermFringeNegExNegStart() { //TODO(broken for other reasons)
        val term: Term = createTerm("-2ln2x^-2");
        assertAll("neg EX LN",
            { assertEquals(true,   term.neg) },
            { assertEquals(4,       term.const) },
            { assertEquals(-2,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(8,       term.calculate(4)) },
            { assertEquals(OuterFunctionFlag.LN, term.flag)},
            { assertEquals(false, term.negEx)}
        )
    }
}