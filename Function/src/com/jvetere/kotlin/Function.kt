package com.jvetere.kotlin

class Function {
    private var hashMap: HashMap<Int, Int> = HashMap()
    operator fun get(x: Int): Int? {
        if (hashMap[x] == null)
            hashMap[x] = this.calculate(x)
        return hashMap[x];
    }
    private fun calculate(x: Int): Int{
        TODO()
    }
}