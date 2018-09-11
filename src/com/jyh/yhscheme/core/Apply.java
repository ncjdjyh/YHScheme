package com.jyh.yhscheme.core;

import com.jyh.yhscheme.bulitfunction.BuiltFunction;
import com.jyh.yhscheme.Expression;
import com.jyh.yhscheme.keyword.*;
import com.jyh.yhscheme.type.Function;
import com.jyh.yhscheme.util.Charset;

import java.util.ArrayList;
import java.util.List;

public class Apply {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/8 15:30
     * @Description:
     */

    /*单个元素求值*/
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

    /*基本过程求值*/
    public static Object essentialEval(Expression exp, Environment env) {
        String operator = exp.getOperator();
        List<Expression> operands = exp.getOperands();
        List<Object> params = new ArrayList<>();
        for (Expression e : operands) {
            params.add(Eval.eval(e, env));
        }
        switch (operator) {
            case Charset.ADD: {
                return BuiltFunction.add(params);
            }
            case Charset.SUB: {
                return BuiltFunction.sub(params);
            }
            case Charset.MUL: {
                return BuiltFunction.mul(params);
            }
            case Charset.DIV: {
                return BuiltFunction.div(params);
            }
            case Charset.GT: {
                return BuiltFunction.gt(params);
            }
            case Charset.LT: {
                return BuiltFunction.lt(params);
            }
            case Charset.EQ: {
                return BuiltFunction.eq(params);
            }
            case Charset.PRINT: {
                return BuiltFunction.display(params);
            }
        }
        return null;
    }

    /*关键字求值*/
    public static Object keywordEval(Expression exp, Environment env) {
        String keyword = exp.getOperator();
        switch (keyword) {
            case Charset.DEF: {
                Def def = new Def(exp);
                return defEval(def, env);
            }
            case Charset.LAMBDA: {
                Lambda lambda = new Lambda(exp);
                return lambdaEval(lambda, env);
            }
            case Charset.IF: {
                If jIf = new If(exp);
                return IfEval(jIf, env);
            }
            case Charset.LET: {
                Let let = new Let(exp);
                return letEval(let, env);
            }
            case Charset.Begin: {
                Begin begin = new Begin(exp);
                return beginEval(begin, env);
            }
        }
        return null;
    }

    private static Object beginEval(Begin begin, Environment env) {
        List<Expression> es = begin.getElements();
        return evalAllAndReturnLastResult(es, env);
    }

    private static Object letEval(Let let, Environment env) {
        List<String> keys = let.getBindKeys();
        List<Object> values = extractEvalParams(let.getBindValues(), env);
        Environment letEnv = null;
        try {
            letEnv = env.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        letEnv.extendEnvironment(keys, values);
        return Eval.eval(let.getBody(), letEnv);
    }

    private static Object IfEval(If jIf, Environment env) {
        Boolean ok = (Boolean) Eval.eval(jIf.getPredicate(), env);
        if (ok) {
            return Eval.eval(jIf.getRealCondition(), env);
        } else {
            return Eval.eval(jIf.getFakeCondition(), env);
        }
    }

    private static Object lambdaEval(Lambda lambda, Environment env) {
        return new Function(lambda.getParams(), lambda.getBody(), env);
    }

    private static Object defEval(Def def, Environment env) {
        env.extendEnvironment(def.getVar(), Eval.eval(def.getVal(), env));
        return "ok";
    }

    /*过程求值*/
    public static Object functionEval(Expression exp, Environment env) {
        List<Object> realParams = getRealParams(exp, env);
        Function func = getFunc(exp, env);
        Environment currentEnv = func.getCurrentEnv();
        //求值过程时创建新环境 就是当前过程闭包的环境 并且指向父环境
        Environment funcEnv = new Environment(currentEnv);
        //扩充求值环境
        funcEnv.extendEnvironment(func.getParams(), realParams);
        return evalAllAndReturnLastResult(func.getBody(), funcEnv);
    }

    private static List<Object> getRealParams(Expression exp, Environment env) {
        List<Object> params = new ArrayList<>();
        List<Expression> expParams = exp.getChildren().subList(1, exp.getChildrenLength() - 1);
        for (Expression e : expParams) {
            params.add(Eval.eval(e, env));
        }
        return params;
    }

    private static Function getFunc(Expression exp, Environment env) {
        return (Function) Eval.eval(exp.getChildren().get(0), env);
    }

    /*提取所有求值以后的参数*/
    private static List<Object> extractEvalParams(List<Expression> es, Environment env) {
        List<Object> params = new ArrayList<>();
        for (Expression e : es) {
            params.add(Eval.eval(e, env));
        }
        return params;
    }

    /*解释所有元素并返回最后一个元素求值的结果*/
    private static Object evalAllAndReturnLastResult(List<Expression> es, Environment env) {
        int lastIndex = es.size() - 1;
        for (int i = 0; i < lastIndex; i++) {
            Eval.eval(es.get(i), env);
        }
        return Eval.eval(es.get(lastIndex), env);
    }
}
