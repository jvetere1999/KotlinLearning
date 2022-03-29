package com.jvetere.kotlin

data class Function (val asd: Int){
    private var hashMap: HashMap<Int, Int> = HashMap()
    operator fun get(x: Int): Int? {
        if (hashMap[x] == null)
            hashMap[x] = this.calculate(x)
        return hashMap[x];
    }
    private fun calculate(x: Int): Int{
        return TODO();
    }
}

fun createFunction(exp: String): Function {
    println(exp.split("+"))

    return TODO("Figure out how to split a function into terms consistently and quickly")
}