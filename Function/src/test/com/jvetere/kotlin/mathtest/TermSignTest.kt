package test.com.jvetere.kotlin.mathtest


import com.jvetere.kotlin.flags.OuterFunctionFlag
import com.jvetere.kotlin.math.Term
import com.jvetere.kotlin.math.createTerm
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.math.PI
import kotlin.test.assertEquals
internal class TermSignTest {
    @Test
    internal fun sinTrust() {
        var exp: String = "sinx";
        var term: Term = createTerm(exp);
        assertAll("sin",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(1.0,       term.calculate((PI/2))) },
            { assertEquals(OuterFunctionFlag.SIN, term.flag) },
        )
        println(StringBuilder("$exp test complete"))
        exp = "sin(2x)"
        term = createTerm(exp)
        assertAll("sin2",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("2x",     term.variable) },
            { assertEquals(0.0,       term.calculate((PI/2))) },
            { assertEquals(OuterFunctionFlag.SIN, term.flag) },
        )
        println(StringBuilder("$exp test complete"))
        exp = "sin(2x^2)"
        term = createTerm(exp)
        assertAll("sin3",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("2x^2",     term.variable) },
            { assertEquals(-.975,       term.calculate((PI/2))) },
            { assertEquals(OuterFunctionFlag.SIN, term.flag) },
        )
        println(StringBuilder("$exp test complete"))
    }
    @Test
    internal fun cosTrust() {
        var exp: String = "cosx"
        var term: Term = createTerm(exp);
        assertAll("cos",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(0.878,       term.calculate((.5))) },
            { assertEquals(OuterFunctionFlag.COS, term.flag) },
        )
        println(StringBuilder("$exp test complete"))
        exp = "cos(2x)"
        term = createTerm(exp)
        assertAll("cos2",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("2x",     term.variable) },
            { assertEquals(0.540,       term.calculate((.5))) },
            { assertEquals(OuterFunctionFlag.COS, term.flag) },
        )
        println(StringBuilder("$exp test complete"))
        exp = "cos(2x^2)"
        term = createTerm(exp)
        assertAll("cos3",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("2x^2",     term.variable) },
            { assertEquals(0.878,       term.calculate((.5))) },
            { assertEquals(OuterFunctionFlag.COS, term.flag) },
        )
        println(StringBuilder("$exp test complete"))
    }
    @Test
    internal fun tanTrust() {
        var exp: String = "tanx"
        var term: Term = createTerm(exp);
        assertAll("tan1",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(-2.185,       term.calculate((2.0))) },
            { assertEquals(OuterFunctionFlag.TAN, term.flag) },
        )
        println(StringBuilder("$exp test complete"))
        exp = "tan(2x)"
        term = createTerm(exp)
        assertAll("tan2",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("2x",     term.variable) },
            { assertEquals(1.158,       term.calculate((2.0))) },
            { assertEquals(OuterFunctionFlag.TAN, term.flag) },
        )
        println(StringBuilder("$exp test complete"))
        exp = "tan(2x^2)"
        term = createTerm(exp)
        assertAll("tan3",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("2x^2",     term.variable) },
            { assertEquals(-6.8,       term.calculate((2.0))) },
            { assertEquals(OuterFunctionFlag.TAN, term.flag) },
        )
        println(StringBuilder("$exp test complete"))
    }
    @Test
    internal fun lnTrust() {
        var exp: String = "lnx"
        var term: Term = createTerm(exp);
        assertAll("ln",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(.693,       term.calculate((2.0))) },
            { assertEquals(OuterFunctionFlag.LN, term.flag) },
        )
        println(StringBuilder("$exp test complete"))
        exp = "ln(2x)"
        term = createTerm(exp)
        assertAll("ln",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("2x",     term.variable) },
            { assertEquals(2.303,       term.calculate((5.0))) },
            { assertEquals(OuterFunctionFlag.LN, term.flag) },
        )
        println(StringBuilder("$exp test complete"))
        exp = "ln(2x^2)"
        term = createTerm(exp)
        assertAll("ln",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("2x^2",     term.variable) },
            { assertEquals(3.912,       term.calculate((5.0))) },
            { assertEquals(OuterFunctionFlag.LN, term.flag) },

        )
        println(StringBuilder("$exp test complete"))
    }
    @Test
    internal fun logTrust() {
        var exp: String = "logx"
        var term: Term = createTerm(exp);
        assertAll("log",
            { assertEquals(false,   term.neg) },
            { assertEquals(1,       term.const) },
            { assertEquals(1,       term.exponent) },
            { assertEquals("x",     term.variable) },
            { assertEquals(0.301,       term.calculate((2.0))) },
            { assertEquals(OuterFunctionFlag.LOG, term.flag) },

        )
        println(StringBuilder("$exp test complete"))
    }
    @Test
    internal fun negTest() {
        var exp1: String = "-sinx";
        var term1: Term = createTerm(exp1);
        var exp2: String = "sin(-2x)";
        var term2: Term = createTerm(exp2);
        var exp3: String = "sin(x^-2)";
        var term3: Term = createTerm(exp3);
        assertAll(
            "sin",
            { assertEquals(1.0, term1.calculate((PI / 2))) },
            { assertEquals(0.0, term2.calculate((PI / 2))) },
            { assertEquals(-.841, term3.calculate((PI / 2))) },
        )
        println(StringBuilder("$exp1 test complete"))
    }
//    @Test
//    internal fun debugging() {
//        var exp = "10sin2x"
//        var term = createTerm(exp)
//        assertAll("sin4",
//            { assertEquals(false,   term.neg) },
//            { assertEquals(2,       term.const) },
//            { assertEquals(1,       term.exponent) },
//            { assertEquals("x",     term.variable) },
//            { assertEquals(9.093,       term.calculate((1.0))) },
//            { assertEquals(OuterFunctionFlag.SIN, term.flag) },
//            { assertEquals(false, term.negEx) },
//            { assertEquals(10,       term.leadingConst) }
//        )
//        println(StringBuilder("$exp test complete"))
//    }
}