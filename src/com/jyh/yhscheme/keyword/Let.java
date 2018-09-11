package com.jyh.yhscheme.keyword;

import com.jyh.yhscheme.Expression;

import java.util.ArrayList;
import java.util.List;

public class Let {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/11 09:18
     * @Description:
     */
    private Expression exp;
    private List<String> bindKeys;
    private List<Expression> bindValues;
    private Expression body;

    public Let(Expression exp) {
        this.exp = exp;
        this.bindKeys = new ArrayList<>();
        this.bindValues = new ArrayList<>();
        this.generate();
    }

    public List<String> getBindKeys() {
        return bindKeys;
    }

    public List<Expression> getBindValues() {
        return bindValues;
    }

    public Expression getBody() {
        return body;
    }

    public void generate() {
        List<Expression> paramsTable = this.exp.getFirstSubExpression().getChildrenExceptBracket();
        for (Expression e : paramsTable) {
            this.bindKeys.add(e.getChildren().get(0).getValue());
            this.bindValues.add(e.getChildren().get(1));
    }

        this.body = this.exp.getChildren().get(2);
    }
}
