package com.jvetere.kotlin

import com.jvetere.kotlin.flags.OuterFunctionFlag
import com.jvetere.kotlin.flags.assignFlag
import org.junit.jupiter.api.assertThrows
import java.lang.StrictMath.pow
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.*


data class Term(val neg  : Boolean, val const : Int, var flag: OuterFunctionFlag, var variable : String, val exponent : Int, var next: Term?) : TypeCastException("ERR: Invalid input!") {
    var negEx: Boolean = false;
    var innerTerm: Term?;
    init {
        negEx = variable.contains('-')
        if (negEx) variable = variable.substring(1);
        innerTerm = if (variable.length > 1) createTerm(variable.replace("(","").replace(")", "")) else null
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

        return BigDecimal(if (neg) -value else value).setScale(3, RoundingMode.HALF_EVEN).toDouble()
    }
    fun derive() : Term? {
        if(exponent == 0)
            return null;
        return if (next != null) {
            when(flag) {
                OuterFunctionFlag.NA    -> Term(neg, const * exponent, flag, variable, exponent - 1, next?.derive())
                OuterFunctionFlag.SIN   -> Term(neg, const * exponent, OuterFunctionFlag.COS, variable, exponent - 1, next?.derive())
                OuterFunctionFlag.COS   -> Term(!neg, const * exponent, OuterFunctionFlag.SIN, variable, exponent - 1, next?.derive())
                OuterFunctionFlag.TAN   -> TODO("Consider adding inner terms for things like cos^2(x^2)")
                OuterFunctionFlag.LN    -> Term(neg, const * exponent, OuterFunctionFlag.NA, variable, exponent - 1, next?.derive())
                OuterFunctionFlag.LOG   -> TODO()
            }
        }
        else {
            Term(neg, const * exponent ,flag , variable, exponent - 1, null)
        }
    }
}

fun createTerm(exp : String) : Term{
    val regex: Regex = Regex("\\(([^\\)]+)\\)|((?:sin)|(?:cos)|(?:tan)|(?:ln)|(?:log)|(?:^[+-])|(?:[-]\\d)|(?:\\d+)|(?:x)|(?:[-][x]))");
    val token: MutableList<String> = regex.findAll(exp).map{ it.groupValues[0] }.toMutableList();
    var neg: Boolean? = false;
    var const: Int? = null;
    var flag: OuterFunctionFlag? =null;
    var variable: String? = null;
    var exponent: Int? = null;
    for (x in token){
        if (isSign(x)) {
            neg = token[0] == "-"
        }
        if (x.matches(Regex("[0-9]+"))){
            if(flag == null && variable == null && const == null)
                const = Integer.parseInt(x);
            else
                exponent = Integer.parseInt(x);
        }
        if (isOp(x))
            flag = assignFlag(x)
        else if (x.matches(Regex("\\(([^\\)]+)\\)|[a-z]"))) {
            variable = x;
        }
    }

    if (flag == null) flag = OuterFunctionFlag.NA;
    if (const == null) const = 1;
    if (exponent == null) exponent = if(variable == null) 0 else 1;
    if (variable == null) variable = "x";
    return Term(neg!!, const, flag, variable!!, exponent, null);
}
fun isSign(s : String): Boolean {
    return !(s.isNullOrEmpty() || !s.matches(Regex("\\d+|[+-]\\d+")));
}
fun isOp(s : String): Boolean {
    return !s.isNullOrEmpty() && s.matches(Regex("sin|cos|tan|ln|log"));
}