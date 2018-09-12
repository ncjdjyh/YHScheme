package com.jyh.yhscheme.keyword;

import com.jyh.yhscheme.Charset;
import com.jyh.yhscheme.Expression;
import com.jyh.yhscheme.core.Environment;
import com.jyh.yhscheme.core.Eval;
import com.jyh.yhscheme.type.Function;
import com.jyh.yhscheme.util.ExpUtil;

import java.util.List;


public class Def {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/9 11:26
     * @Description:
     */
    private Expression exp;
    private String var;
    private Object val;
    private Environment env;

    public Def(Expression exp, Environment env) {
        this.exp = exp;
        this.env = env;
        this.generate();
    }

    /*在这里本来想法是如果是普通过程就转化为lambda过程进行求值
    * 但是没有完成, 一是因为转化比较繁琐, 二是因为效率不高(要再次词法语法分析)*/
    private void generate() {
        boolean isNormalDef = ExpUtil.findChildValue(1, exp).equals(Charset.START_TOKEN);
        if (isNormalDef) {
            Expression funcHead = ExpUtil.findChild(1, exp);
            this.var = funcHead.getOperator();
            List<String> params = ExpUtil.getParamsExceptOperator(funcHead);
            List<Expression> funcBody = ExpUtil.findSubExpression(2, exp);
            this.val = new Function(params, funcBody, env);
        } else {
            this.var = ExpUtil.findChildValue(1, exp);
            this.val = Eval.eval(ExpUtil.findChild(2, exp), env);
        }
    }

    public String getVar() {
        return var;
    }

    public Object getVal() {
        return val;
    }

    private boolean isLambdaDef() {
        return exp.getChildren().get(2).getOperator().equals(Charset.LAMBDA);
    }
}
