package com.jvetere.kotlin.flags

enum class OuterFunctionFlag {
    SIN,
    COS,
    TAN,
    LN,
    LOG,
    NA,
}

fun assignFlag(outerFunction: String) : OuterFunctionFlag {
    return when(outerFunction) {
        "sin" -> OuterFunctionFlag.SIN
        "cos" -> OuterFunctionFlag.COS
        "tan" -> OuterFunctionFlag.TAN
        "ln"  -> OuterFunctionFlag.LN
        "log" -> OuterFunctionFlag.LOG
        else  -> OuterFunctionFlag.NA
    }

}