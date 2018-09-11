package com.jyh.yhscheme.keyword;

import com.jyh.yhscheme.Expression;

public class Def {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/9 11:26
     * @Description:
     */
    private Expression exp;
    private String var;
    private Expression val;

    public Def(Expression exp) {
        this.exp = exp;
        this.generate();
    }

    private void generate() {
        this.var = exp.getChildren().get(1).getValue();
        this.val = exp.getChildren().get(2);
    }

    public String getVar() {
        return this.var;
    }

    public Expression getVal() {
        return this.val;
    }
}
