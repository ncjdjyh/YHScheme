package com.jyh.yhscheme.util;

import com.jyh.yhscheme.Expression;
import com.jyh.yhscheme.core.Environment;
import com.jyh.yhscheme.core.Eval;
import com.jyh.yhscheme.keyword.*;
import com.jyh.yhscheme.type.Function;

import java.util.List;

public class KeywordEvalUtil {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/11 15:54
     * @Description:
     */

    public static Object evalBegin(Expression exp, Environment env) {
        Begin begin = new Begin(exp);
        List<Expression> es = begin.getElements();
        return EvalUtil.evalAllAndReturnLastResult(es, env);
    }

    public static Object evalLet(Expression exp, Environment env) {
        Let let = new Let(exp);
        List<String> keys = let.getBindKeys();
        List<Object> values = EvalUtil.extractEvalParams(let.getBindValues(), env);
        Environment letEnv = null;
        try {
            letEnv = env.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        letEnv.extendEnvironment(keys, values);
        return Eval.eval(let.getBody(), letEnv);
    }

    public static Object evalIf(Expression exp, Environment env) {
        If jIf = new If(exp);
        Boolean ok = (Boolean) Eval.eval(jIf.getPredicate(), env);
        if (ok) {
            return Eval.eval(jIf.getRealCondition(), env);
        } else {
            return Eval.eval(jIf.getFakeCondition(), env);
        }
    }

    public static Object evalLambda(Expression exp, Environment env) {
        Lambda lambda = new Lambda(exp);
        return new Function(lambda.getParams(), lambda.getBody(), env);
    }

    public static Object evalDef(Expression exp, Environment env) {
        Def def = new Def(exp, env);
        env.extendEnvironment(def.getVar(), def.getVal());
        return "ok";
    }
}
