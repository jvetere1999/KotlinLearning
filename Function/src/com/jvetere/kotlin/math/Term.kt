package com.jvetere.kotlin.math

import com.jvetere.kotlin.flags.OuterFunctionFlag
import com.jvetere.kotlin.flags.StringForm
import com.jvetere.kotlin.flags.assignFlag
import java.lang.StrictMath.pow
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.*


data class Term(val neg  : Boolean, val const : Int, var flag: OuterFunctionFlag, var variable : String, val exponent : Int, var next: Term?) : TypeCastException("ERR: Invalid input!") {
    var innerTerm: Term? = if (variable.length > 1) createTerm(variable) else null;
    val form: StringForm = StringForm.TERM // Outer use of our purposes

    //TODO Could proably move this to op flags
    fun calculate(input: Double) : Double {
        var x: Double = if (innerTerm != null) innerTerm!!.calculate(input) else input

        var value: Double = when (flag) {
            OuterFunctionFlag.NA    -> const * pow(x, exponent.toDouble())
            OuterFunctionFlag.SIN   -> const * pow(sin(x), exponent.toDouble())
            OuterFunctionFlag.COS   -> const * pow(cos(x), exponent.toDouble())
            OuterFunctionFlag.TAN   -> const * pow(tan(x), exponent.toDouble())
            OuterFunctionFlag.LN    -> const * pow(ln(x), exponent.toDouble())
            OuterFunctionFlag.LOG   -> const * pow(log(x,10.0), exponent.toDouble())
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
    override fun toString(): String {
        var sb: StringBuilder = StringBuilder()
        if (neg)
            sb.append("-")

        if (const != 1 && exponent != 0)
            sb.append(const)
        else if (exponent == 0)
            sb.append(const)
        sb.append(flag.symbol)
        if (flag != OuterFunctionFlag.NA) {
            if (exponent == 1)
                sb.append("$variable)")
            if (exponent > 1 || exponent < 0)
                sb.append("$variable)^$exponent")
        }
        else {
            if (exponent == 1)
                sb.append("$variable")
            if (exponent > 1 || exponent < 0)
                sb.append("$variable^$exponent")
        }
        if (hasNext())
            sb.append(" + ")
        return sb.toString()
    }
    fun hasNext(): Boolean {
        return next != null;
    }
    operator fun invoke(x: Double): Double {
        val calc:Double = calculate(x)
        return calc;
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
            variable = x.replace("(","").replace(")", "");
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