package com.jyh.yhscheme.keyword;

import com.jyh.yhscheme.Expression;

public class If {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/10 23:09
     * @Description:
     */
    private Expression exp;
    private Expression predicate;
    private Expression realCondition;
    private Expression fakeCondition;

    public If(Expression exp) {
        this.exp = exp;
        this.generate();
    }

    private void generate() {
        this.predicate = exp.findChild(1);
        this.realCondition = exp.findChild(2);
        this.fakeCondition = exp.findChild(3);
    }

    public Expression getExp() {
        return exp;
    }

    public Expression getPredicate() {
        return predicate;
    }

    public Expression getRealCondition() {
        return realCondition;
    }

    public Expression getFakeCondition() {
        return fakeCondition;
    }
}
