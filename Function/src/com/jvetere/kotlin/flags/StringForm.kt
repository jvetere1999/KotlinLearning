package com.jvetere.kotlin.flags

enum class StringForm(val form:String) {
    TERM("{[-]|[0-9]+|[flag]|[a-z][^][0-9]+"),
    FUNCTION("{}+{}+{}+{}+...")
}
