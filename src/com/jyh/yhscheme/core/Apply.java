package com.jyh.yhscheme.core;

import com.jyh.yhscheme.bulitfunction.BuiltFunction;
import com.jyh.yhscheme.Expression;
import com.jyh.yhscheme.keyword.Def;
import com.jyh.yhscheme.type.Function;
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
            Object result = env.findVariable(val);
            if (result != null) return result;
            else {
                System.err.println("Error token" + val);
                return null;
            }
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
                return BuiltFunction.add(params);
            }
            case Constant.SUB: {
                return BuiltFunction.sub(params);
            }
            case Constant.MUL: {
                return BuiltFunction.mul(params);
            }
            case Constant.DIV: {
                return BuiltFunction.div(params);
            }
        }
        return null;
    }

    public static Object keywordEval(Expression exp, Environment env) {
        String keyword = exp.getOperator();
        switch (keyword) {
            case Constant.DEF: {
                Def def = new Def(exp);
                return defEval(def, env);
            }
            case Constant.LAMBDA: {
                Function func = new Function(exp, env);
                return func;
            }
        }
        return null;
    }

    private static Object defEval(Def def, Environment env) {
        env.extendEnvironment(def.getVar(), Eval.eval(def.getVal(), env));
        return "ok";
    }

    public static Object functionEval(Expression exp, Environment env) {
        List<Object> realParams = getRealParams(exp, env);
        Function func = getFunc(exp, env);
        Environment currentEnv = func.getCurrentEnv();
        //求值过程时创建新环境 就是当前过程闭包的环境 并且指向父环境
        Environment funcEnv = new Environment(currentEnv);
        //扩充求值环境
        funcEnv.extendEnvironment(func.getParams(), realParams);
        return Eval.eval(func.getBody(), funcEnv);
    }

    private static List<Object> getRealParams(Expression exp, Environment env) {
        List<Object> params = new ArrayList<>();
        List<Expression> expParams = exp.getChildren().subList(1, exp.getChildrenLength() - 1);
        for (Expression e: expParams) {
            params.add(Eval.eval(e, env));
        }
        return params;
    }

    private static Function getFunc(Expression exp, Environment env) {
        return (Function) Eval.eval(exp.getChildren().get(0), env);
    }
}
