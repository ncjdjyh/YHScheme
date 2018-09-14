package com.jyh.yhscheme.core;

import com.jyh.yhscheme.Expression;
import com.jyh.yhscheme.util.EnvUtil;

public class Eval {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/8 15:30
     * @Description: 求值器
     */
    public static Object eval(Expression exp, Environment env) {
        if (isSelfEval(exp)) {
            return Apply.evalSelf(exp, env);
        } else if (isKeywordEval(exp)) {
            return Apply.keywordEval(exp, env);
        } else if (isEssentialEval(exp) ){
            return Apply.essentialEval(exp, env);
        } else {
            return Apply.evalProcedure(exp, env);
        }
    }

    private static boolean isSelfEval(Expression exp) {
        return exp.getChildren().size() == 0;
    }

    private static boolean isKeywordEval(Expression exp) {
        return EnvUtil.keywordSet.containsKey(exp.getOperator());
    }

    private static boolean isEssentialEval(Expression exp) {
        return EnvUtil.builtMap.containsKey(exp.getOperator());
    }
}
