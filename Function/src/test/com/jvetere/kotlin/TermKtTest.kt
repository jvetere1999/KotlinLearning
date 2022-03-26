package test.com.jvetere.kotlin

import com.jvetere.kotlin.Term
import com.jvetere.kotlin.createTerm
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

internal class TermKtTest {

    @Test
    internal fun BasicTerm() {
        val term: Term = createTerm("2x^2");
        assertAll("Basic",
            { assertEquals(false, term.ne) },
            )
    }
}