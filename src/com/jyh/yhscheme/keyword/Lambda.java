package com.jyh.yhscheme.keyword;

import com.jyh.yhscheme.Expression;

import java.util.List;

public class Lambda {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/10 23:09
     * @Description:
     */
    private Expression exp;
    private List<String> params;
    private List<Expression> body;

    public Lambda(Expression exp) {
        this.exp = exp;
        this.generate();
    }

    private void generate() {

        this.params = exp.findChild(1).getParams();
        this.body = exp.findSubExpression(2);
    }

    public List<String> getParams() {
        return this.params;
    }

    public List<Expression> getBody() {
        return this.body;
    }
}
