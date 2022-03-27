package com.jvetere.kotlin

import org.junit.jupiter.api.assertThrows
import java.lang.StrictMath.pow

data class Term(val neg  : Boolean, val const : Int, var variable : String, val exponent : Int, var next: Term?, var flag: OuterFunctionFlag) : TypeCastException("ERR: Invalid input!") {
    var negEx: Boolean = false;
    init {
        negEx = variable.contains('-')
        if (negEx) variable = variable.substring(1);
    }
    fun calculate(x: Int) : Int {
        val value: Double = const * (pow(x.toDouble(), exponent.toDouble()));
        return (if (neg) -value else value).toInt()
        //TODO("Add calculate by flag")
    }
    fun derive() : Term? {
        if(exponent == 0)
            return null;
        //TODO("Add functionality for deriving functions by flag");
        return if (next != null)
            Term(neg, const * exponent, variable, exponent-1, next?.derive(), flag)
        else
            Term(neg, const * exponent, variable, exponent-1, next?.derive(), flag)

    }
    fun varNeg(): Boolean {
        return negEx;
    }
}


fun createTerm(exp : String) : Term{
    val regex: Regex = Regex("((?:sin)|(?:cos)|(?:tan)|(?:ln)|(?:log)|(?:^[+-])|(?:[-]\\d)|(?:\\d+)|(?:x)|(?:[-][x]))")
    val matches: Sequence<MatchResult> = regex.findAll(exp)
    val token: MutableList<String> = matches.map{ it.groupValues[1] }.toMutableList()
    var ne: Boolean = false
    var flag: OuterFunctionFlag = OuterFunctionFlag.NA
    var constMultiplyer: Int = 1;
    //TODO("split these off into sub functioins"):
    if (token.contains("-") || token.contains("+")) {
        ne = token[0] == "-"
        token.removeAt(0)
    }
    if (hasOp(token)) {
        flag = assignFlag(token[0])
        token.removeAt(0)
    }
    if (isNumeric(token[0]) && token.size > 2){
        constMultiplyer = Integer.parseInt(token[0])
        token.removeAt(0);
    }
    val term : Term = when (token.size) {
        3 -> { Term(ne, (Integer.parseInt(token[0])*constMultiplyer) , token[1], Integer.parseInt(token[2]), null, flag) }
        2 -> {
            if (isNumeric(token[0]))
                Term(ne, (Integer.parseInt(token[0])*constMultiplyer), token[1], 1, null, flag)
            else
                Term(ne, (1*constMultiplyer), token[0], Integer.parseInt(token[1]), null, flag)
        }
        1 -> {
            if (isNumeric(token[0]))
                Term(ne, (Integer.parseInt(token[0])*constMultiplyer), "x", 0, null, flag)
            else
                Term(ne, (1*constMultiplyer), token[0], 1, null, flag)
        }
        else -> assertThrows<Term> {}
    }
    return term
}

fun isNumeric(s : String): Boolean {
    return !s.isNullOrEmpty() && s.matches(Regex("\\d+"))
}
fun hasOp(l : MutableList<String>): Boolean {
    if (l.isNullOrEmpty()) return false
    for (s in l) {
        if (s.matches(Regex("sin|cos|tan|ln|log")))
            return true;
    }
    return false
}