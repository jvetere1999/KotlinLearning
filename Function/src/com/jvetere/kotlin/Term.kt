package com.jvetere.kotlin

import org.junit.jupiter.api.assertThrows

data class Term(val ne  : Boolean, val const : Int, val variable : Char, val exponent : Int) : Exception("ERR: Invalid input!");

fun createTerm(exp : String) : Term{
    val regex: Regex = Regex("((?:[+-])|(?:\\d+)|(?:x))");
    val token: List<Any> = regex.findAll(exp).toList();
    if (token[0] == "-")
        TODO( "Figure out how to remove the element from the beging of array if is a sign and create the neg value from that")
    val term : Term = when (token.size) {
        3 -> { Term(token[0] == "-", token[1] as Int, token[2] as Char, token[3] as Int) }
        2 -> { TODO( "Implement 2 char long parsing for term")}
        1 -> { TODO("Implement 1 char long parsing for term")}

        else -> { assertThrows<Term> {}}
    }
    return Term(true,1, 'x',1);
}

fun isNumeric( s : String?): Boolean {
    return !s.isNullOrEmpty() && s.matches(Regex("\\d+"));
}