package com.jyh.yhscheme.keyword;

import com.jyh.yhscheme.Expression;

import java.util.List;

public class Cond {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/12 15:45
     * @Description:
     */
    private Expression exp;
    private List<Expression> conditions;

    public Cond(Expression exp) {
        this.exp = exp;
    }

    public List<Expression> getConditions() {
        return exp.getChildrenExceptBracketAndOperator();
    }
}
