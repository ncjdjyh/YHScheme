package com.jyh.yhscheme.core;

import com.jyh.yhscheme.Expression;
import com.jyh.yhscheme.type.Procedure;
import com.jyh.yhscheme.util.EnvUtil;
import com.jyh.yhscheme.util.EvalUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Apply {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/8 15:30
     * @Description:
     */

    /*单个元素求值*/
    public static Object evalSelf(Expression exp, Environment env) {
        String val = exp.getValue();
        if (val.matches("\\d+")) {
            return new Integer(val);
        } else {
            Object result = env.findVariable(val);
            if (result != null) return result;
            else {
                System.err.println("Error token  " + val);
                return null;
            }
        }
    }

    /*基本过程求值*/
    public static Object essentialEval(Expression exp, Environment env) {
        String operator = exp.getOperator();
        List<Expression> operands = exp.getOperands();
        List<Object> params = EvalUtil.extractEvalParams(operands, env);
        return executeBuiltProcedure(operator, params);
    }

    private static Object executeBuiltProcedure(String operator, List<Object> params) {
        String className = "com.jyh.yhscheme.BuiltProcedure";
        String methodName = EnvUtil.builtMap.get(operator);
        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getMethod(methodName, List.class);
            return method.invoke(clazz, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object executeKeywordProcdure(String keyword, Expression exp, Environment env) {
        String className = "com.jyh.yhscheme.util.KeywordEvalUtil";
        String methodName = EnvUtil.keywordSet.get(keyword);
        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getDeclaredMethod(methodName, Expression.class, Environment.class);
            return method.invoke(clazz, exp, env);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*关键字求值*/
    public static Object keywordEval(Expression exp, Environment env) {
        String keyword = exp.getOperator();
        return executeKeywordProcdure(keyword, exp, env);
    }

    /*过程求值*/
    public static Object evalProcedure(Expression exp, Environment env) {
        List<Object> realParams = getRealParams(exp, env);
        Procedure func = getProc(exp, env);
        Environment currentEnv = func.getCurrentEnv();
        //求值过程时创建新环境 就是当前过程闭包的环境 并且指向父环境
        Environment funcEnv = new Environment(currentEnv);
        //扩充求值环境
        funcEnv.extendEnvironment(func.getParams(), realParams);
        return EvalUtil.evalAllAndReturnLastResult(func.getBody(), funcEnv);
    }

    private static List<Object> getRealParams(Expression exp, Environment env) {
        List<Object> params = new ArrayList<>();
        List<Expression> expParams = exp.getChildrenExceptBracketAndOperator();
        for (Expression e : expParams) {
            params.add(Eval.eval(e, env));
        }
        return params;
    }

    private static Procedure getProc(Expression exp, Environment env) {
        return (Procedure) Eval.eval(exp.getChildren().get(0), env);
    }
}
