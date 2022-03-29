package test.com.jvetere.kotlin

import com.jvetere.kotlin.flags.OuterFunctionFlag
import com.jvetere.kotlin.flags.assignFlag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class ConceptTest {

//   @Test
//    internal fun regex() {
//        val exp: String = "sin(-2x^3)^-3";
//        val regex: Regex = Regex("\\(([^\\)]+)\\)|((?:sin)|(?:cos)|(?:tan)|(?:ln)|(?:log)|(?:^[+-])|(?:[-]\\d)|(?:\\d+)|(?:x)|(?:[-][x]))");
//        val matches: Sequence<MatchResult> = regex.findAll(exp);
//        val token: MutableList<String> = matches.map{ it.groupValues[0] }.toMutableList();
//        var neg: Boolean? = false;
//        var const: Int? = null;
//        var flag: OuterFunctionFlag? =null;
//        var variable: String? = null;
//        var exponent: Int? = null;
//        for (x in token){
//            if (isSign(x)) {
//                neg = token[0] == "-"
//            }
//            if (x.matches(Regex("[0-9]+"))){
//                if(flag == null && variable == null && const == null)
//                    const = Integer.parseInt(x);
//                else
//                    exponent = Integer.parseInt(x);
//            }
//            println(x)
//            if (isOp(x))
//                flag = assignFlag(x)
//            else if (x.matches(Regex("\\(([^\\)]+)\\)|[a-z]"))) {
//                variable = x;
//            }
//        }
//       if (flag == null) flag = OuterFunctionFlag.NA;
//       if (const == null)
//           const = 1;
//       if (exponent == null)
//           exponent = if(variable == null) 0 else 1
//
//    }
//    fun isSign(s : String): Boolean {
//        return !(s.isNullOrEmpty() || !s.matches(Regex("\\d+|[+-]\\d+")));
//    }
//    fun isOp(s : String): Boolean {
//        return !s.isNullOrEmpty() && s.matches(Regex("sin|cos|tan|ln|log"));
//    }
}