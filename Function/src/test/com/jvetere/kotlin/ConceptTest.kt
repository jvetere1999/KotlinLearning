package test.com.jvetere.kotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class ConceptTest {

    @Test
    internal fun regex() {
        val exp: String = "+2x^2"
        val regex: Regex = Regex("\\(([^\\)]+)\\)|((?:sin)|(?:cos)|(?:tan)|(?:ln)|(?:log)|(?:^[+-])|(?:[-]\\d)|(?:\\d+)|(?:x)|(?:[-][x]))")
        val matches: Sequence<MatchResult> = regex.findAll(exp)
        val token: MutableList<String> = matches.map{ it.groupValues[0] }.toMutableList()
        println(token.toString())
        assertAll("Regex Tests")
    }
}//((?:sin)|(?:cos)|(?:tan)|(?:ln)|(?:log)|(?:^[+-])|(?:[-]\d)|(?:\d+)|(?:x)|(?:[-][x]))