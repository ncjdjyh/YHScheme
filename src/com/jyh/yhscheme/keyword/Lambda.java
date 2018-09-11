package com.jyh.yhscheme.keyword;

import com.jyh.yhscheme.Expression;

import java.util.ArrayList;
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
        List<String> params = new ArrayList<>();
        List<Expression> expParams = exp.getChildren().get(1).getChildren().subList(0, exp.getChildren().get(1).getChildren().size() - 1);
        for (Expression e : expParams) {
            params.add(e.getValue());
        }
        this.params = params;
        this.body = exp.getChildren().subList(2, exp.getChildrenLength() - 1);
    }

    public List<String> getParams() {
        return this.params;
    }

    public List<Expression> getBody() {
        return this.body;
    }
}
