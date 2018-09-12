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
        String[] tokens = Parser.getTokens(ifTest);
        Expression exp = Parser.parse(tokens);
        Assert.assertEquals(Eval.eval(exp, env), 2);
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
        String[] tokens1 = Parser.getTokens(src1);
        Expression exp1 = Parser.parse(tokens1);
        Eval.eval(exp1, env);
        String[] tokens2 = Parser.getTokens(src2);
        Expression exp2 = Parser.parse(tokens2);
        Assert.assertEquals(Eval.eval(exp2, env), 11);
    }

    @Test
    public void letTest() {
        String src = "(let ((x 1) (y 2)) (+ x y))";
        String[] tokens = Parser.getTokens(src);
        Expression exp = Parser.parse(tokens);
        Assert.assertEquals(Eval.eval(exp, env), 3);
    }
}