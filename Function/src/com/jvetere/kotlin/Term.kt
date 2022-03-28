package com.jvetere.kotlin

import com.jvetere.kotlin.flags.OuterFunctionFlag
import com.jvetere.kotlin.flags.assignFlag
import org.junit.jupiter.api.assertThrows
import java.lang.StrictMath.pow
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.*


data class Term(val neg  : Boolean, val const : Int, var variable : String, val exponent : Int, var next: Term?, var flag: OuterFunctionFlag, val leadingConst: Int) : TypeCastException("ERR: Invalid input!") {
    var negEx: Boolean = false;
    init {
        negEx = variable.contains('-')
        if (negEx) variable = variable.substring(1);
    }
    fun calculate(x: Double) : Double {
        var value: Double = when (flag) {
            OuterFunctionFlag.NA    -> if (!negEx) (const * (pow(x.toDouble(), exponent.toDouble()))) else const * (pow((x.toDouble() * -1), exponent.toDouble()))
            OuterFunctionFlag.SIN   -> if (!negEx) sin(const * (pow(x.toDouble(), exponent.toDouble()))) else sin(const * (pow((x.toDouble() * -1), exponent.toDouble())))
            OuterFunctionFlag.COS   -> if (!negEx) cos(const * (pow(x.toDouble(), exponent.toDouble()))) else cos(const * (pow((x.toDouble() * -1), exponent.toDouble())))
            OuterFunctionFlag.TAN   -> if (!negEx) tan(const * (pow(x.toDouble(), exponent.toDouble()))) else tan(const * (pow((x.toDouble() * -1), exponent.toDouble())))
            OuterFunctionFlag.LN    -> if (!negEx) ln(const * (pow(x.toDouble(), exponent.toDouble()))) else ln(const * (pow((x.toDouble() * -1), exponent.toDouble())))
            OuterFunctionFlag.LOG   -> if (!negEx) log(const * (pow(x.toDouble(), exponent.toDouble())), 10.0) else log(const * (pow((x.toDouble() * -1), exponent.toDouble())),10.0)
        }
        if (leadingConst > 1) {
            value *= leadingConst
        }
        return BigDecimal(if (neg) -value else value).setScale(3, RoundingMode.HALF_EVEN).toDouble()
    }
    fun derive() : Term? {
        if(exponent == 0)
            return null;
        return if (next != null) {
            when(flag) {
                OuterFunctionFlag.NA    -> Term(neg, const * exponent, variable, exponent - 1, next?.derive(), flag, leadingConst)
                OuterFunctionFlag.SIN   -> Term(neg, const * exponent, variable, exponent - 1, next?.derive(), OuterFunctionFlag.COS, leadingConst)
                OuterFunctionFlag.COS   -> Term(!neg, const * exponent, variable, exponent - 1, next?.derive(), OuterFunctionFlag.SIN, leadingConst)
                OuterFunctionFlag.TAN   -> TODO("Consider adding inner terms for things like cos^2(x^2)")
                OuterFunctionFlag.LN    -> Term(neg, const * exponent, variable, exponent - 1, next?.derive(), OuterFunctionFlag.NA, leadingConst)
                OuterFunctionFlag.LOG   -> TODO()
            }
        }
        else {
            Term(neg, const * exponent, variable, exponent - 1, null, flag, leadingConst)
        }
    }
    fun varNeg(): Boolean {
        return negEx;
    }
}

    //TODO(Again make smaller)
fun createTerm(exp : String) : Term{
    val regex: Regex = Regex("\\(([^\\)]+)\\)|((?:sin)|(?:cos)|(?:tan)|(?:ln)|(?:log)|(?:^[+-])|(?:[-]\\d)|(?:\\d+)|(?:x)|(?:[-][x]))")
    val matches: Sequence<MatchResult> = regex.findAll(exp)
    val token: MutableList<String> = matches.map{ it.groupValues[1] }.toMutableList()
    var ne: Boolean = false
    var flag: OuterFunctionFlag = OuterFunctionFlag.NA
    var outter: Int = 1;
    if (token.contains("-") || token.contains("+")) {
        ne = token[0] == "-"
        token.removeAt(0)
    }
    if (isNumeric(token[0]) && token.size > 3){
        outter = Integer.parseInt(token[0])
        token.removeAt(0);
    }
    if (hasOp(token)) {
        flag = assignFlag(token[0])
        token.removeAt(0)
    }
    val term : Term = when (token.size) {
        3 -> { Term(ne, (Integer.parseInt(token[0])), token[1], Integer.parseInt(token[2]), null, flag, outter) }
        2 -> {
            if (isNumeric(token[0])) Term(ne, (Integer.parseInt(token[0])), token[1], 1, null, flag, outter)
            else Term(ne, 1, token[0], Integer.parseInt(token[1]), null, flag, outter)
        }
        1 -> {
            if (isNumeric(token[0])) Term(ne, (Integer.parseInt(token[0])), "x", 0, null, flag, outter)
            else Term(ne, (1), token[0], 1, null, flag, outter)
        }
        else -> assertThrows<Term> {}
    }
    return term
}

fun isNumeric(s : String): Boolean {
    return !s.isNullOrEmpty() && s.matches(Regex("\\d+|[+-]\\d+"))
}
fun hasOp(l : MutableList<String>): Boolean {
    if (l.isNullOrEmpty()) return false
    for (s in l) {
        if (s.matches(Regex("sin|cos|tan|ln|log")))
            return true;
    }
    return false
}