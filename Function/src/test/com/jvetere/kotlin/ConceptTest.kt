package test.com.jvetere.kotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class ConceptTest {

   @Test
    internal fun regex() {
        val exp: String = "+2x^2";
        val regex: Regex = Regex("\\(([^\\)]+)\\)|((?:sin)|(?:cos)|(?:tan)|(?:ln)|(?:log)|(?:^[+-])|(?:[-]\\d)|(?:\\d+)|(?:x)|(?:[-][x]))");
        val matches: Sequence<MatchResult> = regex.findAll(exp);
        val token: MutableList<String> = matches.map{ it.groupValues[0] }.toMutableList();
        var neg: Boolean?;
        var const: Int?;
        var flag: OuterFunctionFlag?;
        var variable: String?;
        var expopnent: Int?;
        for (x in token){
            if (isSign(x))
                neg = token[0] == "-"
            if (x.matches(Regex("[0-9]+"))){
                if(flag == null && variable == null && const == null)
                    const = Integer.pasrseInt(x);
                else
                    exponent = Interger.parseInt(x);
            }
            if (isOp(x))
                flag = assignOp(x)
        }
        println("$neg $const $flag $variable $exponent")
        assertAll("Regex Tests")
    }
    fun isSign(s : String): Boolean {
        return !s.isNullOrEmpty() && s.matches(Regex("\\d+|[+-]\\d+"));
    }
    fun isOp(s : String): Boolean {
        return !s.isNullOrEmpty() && s.matches(Regex("sin|cos|tan|ln|log"));
    }
}