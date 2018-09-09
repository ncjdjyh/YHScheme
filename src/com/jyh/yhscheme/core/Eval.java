package com.jyh.yhscheme.core;

import com.jyh.yhscheme.Expression;

public class Eval {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/8 15:30
     * @Description:
     */
    public static Object eval(Expression exp, Environment env) {
        if (isSelfEval(exp)) {
            return Apply.selfEval(exp, env);
        } else {
            return Apply.essentialEval(exp, env);
        }
    }

    private static boolean isSelfEval(Expression exp) {
        return exp.getChildren().size() == 0;
    }

}
