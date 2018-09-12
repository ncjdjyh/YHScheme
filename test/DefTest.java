import com.jyh.yhscheme.Expression;
import com.jyh.yhscheme.Parser;
import com.jyh.yhscheme.core.Environment;
import com.jyh.yhscheme.core.Eval;
import com.jyh.yhscheme.util.EnvUtil;
import org.junit.Assert;
import org.junit.Test;

public class DefTest {
    EnvUtil envUtil = new EnvUtil();
    Environment env = envUtil.initEnv();

    @Test
    public void ifTest() {
        String ifTest = "(if (> 1 2) 1 2)";
        Assert.assertEquals(go(ifTest, env), 2);
    }

    @Test
    public void funcTest() {
        String callFunc = "((lambda (x y) (+ x y)) 2 3)";
        String[] tokens = Parser.getTokens(callFunc);
        Expression exp = Parser.parse(tokens);
        Assert.assertEquals(Eval.eval(exp, env), 5);
    }

    @Test
    public void defineTest() {
        String src1 = "(def (sum x y) (+ x y) (+ 5 6))";
        String src2 = "(sum 2 3)";
        go(src1, env);
        Assert.assertEquals(go(src2, env), 11);
    }

    @Test
    public void letTest() {
        String src = "(let ((x 1) (y 2)) (+ x y))";
        String[] tokens = Parser.getTokens(src);
        Expression exp = Parser.parse(tokens);
        Assert.assertEquals(Eval.eval(exp, env), 3);
    }

    @Test
    public void condTest() {
        String src = "(def (f x)(cond ((= x 1) 1)((= x 2) 2) (3)))";
        String[] tokens = Parser.getTokens(src);
        Expression exp = Parser.parse(tokens);
        Eval.eval(exp, env);

        String src2 = "(f 5)";
        String[] tokens2 = Parser.getTokens(src2);
        Expression exp2 = Parser.parse(tokens2);

        Assert.assertEquals(Eval.eval(exp2, env), 3);
    }

    @Test
    public void closureTest() {
        String src = "(def fibs\n" +
                "  (lambda(n)\n" +
                "    (cond ((= n 1) 0)\n" +
                "          ((= n 2) 1)\n" +
                "           ((+ (fibs (- n 1))\n" +
                "              (fibs (- n 2)))))))\n".replaceAll(System.getProperty("line.separator"), "");
        String[] tokens = Parser.getTokens(src);
        Expression exp = Parser.parse(tokens);
        Eval.eval(exp, env);

        String src2 = "(fibs 10)";
        String[] tokens2 = Parser.getTokens(src2);
        Expression exp2 = Parser.parse(tokens2);

        Assert.assertEquals(Eval.eval(exp2, env), 34);

    }

    private Object go(String src, Environment env) {
        String[] tokens = Parser.getTokens(src);
        Expression exp = Parser.parse(tokens);
        return Eval.eval(exp, env);
    }
}