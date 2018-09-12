package com.jyh.yhscheme.keyword;

import com.jyh.yhscheme.Expression;
import com.jyh.yhscheme.util.ExpUtil;

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
        this.params = ExpUtil.getParams(ExpUtil.findChild(1, exp));
        this.body = ExpUtil.findSubExpression(2, exp);
    }

    public List<String> getParams() {
        return this.params;
    }

    public List<Expression> getBody() {
        return this.body;
    }
}
