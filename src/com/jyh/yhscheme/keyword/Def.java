package com.jyh.yhscheme.keyword;

import com.jyh.yhscheme.Charset;
import com.jyh.yhscheme.Expression;
import com.jyh.yhscheme.core.Environment;
import com.jyh.yhscheme.core.Eval;
import com.jyh.yhscheme.type.Procedure;

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
        boolean isNormalDef = exp.findChildValue(1).equals(Charset.START_TOKEN);
        if (isNormalDef) {
            Expression funcHead = exp.findChild(1);
            this.var = funcHead.getOperator();
            List<String> params = funcHead.getParamsExceptOperator();
            List<Expression> funcBody = exp.findSubExpression(2);
            this.val = new Procedure(params, funcBody, env);
        } else {
            this.var = exp.findChildValue(1);
            this.val = Eval.eval(exp.findChild(2), env);
        }
    }

    public String getVar() {
        return var;
    }

    public Object getVal() {
        return val;
    }
}
