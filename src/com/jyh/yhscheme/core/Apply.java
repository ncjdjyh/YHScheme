package com.jyh.yhscheme.core;

import com.jyh.yhscheme.Built;
import com.jyh.yhscheme.Expression;
import com.jyh.yhscheme.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class Apply {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/8 15:30
     * @Description:
     */
    public static Object selfEval(Expression exp, Environment env) {
        String val = exp.getValue();
        if (val.matches("\\d+")) {
            return new Integer(val);
        } else {
            System.err.println("Error token" + val);
            return null;
        }
    }

    public static Object essentialEval(Expression exp, Environment env) {
        /**
         * 功能描述: 内置过程应用
         * @param: [exp, env]
         * @return: java.lang.Object
         * @auther: ncjdjyh
         * @date: 2018/9/8
         */
        String operator = exp.getOperator();
        List<Expression> operands = exp.getOperands();
        List<Object> params = new ArrayList<>();
        for (Expression e: operands) {
            params.add(Eval.eval(e, env));
        }
        switch (operator) {
            case Constant.ADD: {
                return Built.add(params);
            }
            case Constant.SUB: {
                return Built.sub(params);
            }
            case Constant.MUL: {
                return Built.mul(params);
            }
            case Constant.DIV: {
                return Built.div(params);
            }
        }
        return 0;
    }
}
