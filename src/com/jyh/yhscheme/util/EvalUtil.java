package com.jyh.yhscheme.util;

import com.jyh.yhscheme.Expression;
import com.jyh.yhscheme.core.Environment;
import com.jyh.yhscheme.core.Eval;

import java.util.ArrayList;
import java.util.List;

public class EvalUtil {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/11 20:17
     * @Description:
     */

    /*提取所有list中求值以后的参数*/
    public static List<Object> extractEvalParams(List<Expression> es, Environment env) {
        List<Object> params = new ArrayList<>();
        for (Expression e : es) {
            params.add(Eval.eval(e, env));
        }
        return params;
    }

    /*解释所有元素并返回最后一个元素求值的结果*/
    public static Object evalAllAndReturnLastResult(List<Expression> es, Environment env) {
        int lastIndex = es.size() - 1;
        for (int i = 0; i < lastIndex; i++) {
            Eval.eval(es.get(i), env);
        }
        return Eval.eval(es.get(lastIndex), env);
    }
}
