package com.jvetere.kotlin.flags

import java.util.*

enum class OuterFunctionFlag(val symbol: String) {
    SIN("Sin("),
    COS("Cos("),
    TAN("Tan("),
    LN("Ln("),
    LOG("Log("),
    NA(""),
}

fun assignFlag(outerFunction: String) : OuterFunctionFlag {
    return when(outerFunction.lowercase(Locale.getDefault())) {
        "sin" -> OuterFunctionFlag.SIN
        "cos" -> OuterFunctionFlag.COS
        "tan" -> OuterFunctionFlag.TAN
        "ln"  -> OuterFunctionFlag.LN
        "log" -> OuterFunctionFlag.LOG
        else  -> OuterFunctionFlag.NA
    }

}