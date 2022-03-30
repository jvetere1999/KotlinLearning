package com.jvetere.kotlin.math

import com.jvetere.kotlin.flags.StringForm
import java.util.stream.IntStream.range


/**
 * Circular linked list of terms
 */
data class Function(val head: Term, val butt: Term) {
    var pointer: Term = head;
    private var hashMap: HashMap<Int,Double> = HashMap()
    val form: StringForm = StringForm.FUNCTION;

    operator fun get(x: Int): Double? {
        if (hashMap[x] == null)
            hashMap[x] = this.calculate(x.toDouble())
        return hashMap[x]
    }
    private fun calculate(x: Double): Double{
        var total: Double = 0.0;
        pointer = head
        while (pointer.hasNext()) {
            total += pointer(x)
            pointer = pointer.next!!
        }
        total += pointer(x)
        return total;
    }
    fun nextTerm(): Term {
        pointer = if (pointer.next == null)
            head;
        else
            pointer.next!!;
        return pointer;
    }

    override fun toString(): String {
        var sb: StringBuilder = StringBuilder()
        pointer = head
        while (pointer.hasNext()) {
            sb.append(pointer)
            pointer = pointer.next!!
        }
        sb.append(pointer)
        return sb.toString()
    }
    operator fun invoke(x: Int): Double? {
        return get(x)
    }
}

fun createFunction(exp: String): Function {
    val termString: List<String> = exp.split("+")
    val head: Term = createTerm(termString[0])
    var prev: Term = head
    for ( x in range(1, termString.size) ){
        prev.next = createTerm(termString[x])
        prev = prev.next!!;
    }
    val butt: Term = prev
    return Function(head, butt)
}