package com.jyh.yhscheme.keyword;

import com.jyh.yhscheme.Expression;

import java.util.ArrayList;
import java.util.List;

public class Begin {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/11 11:16
     * @Description:
     */
    private Expression exp;
    private List<Expression> elements;

    public Begin(Expression exp) {
        this.exp = exp;
        elements = new ArrayList<>();
        this.generate();
    }

    private void generate() {
        this.elements = this.exp.getChildren().subList(1, this.exp.getChildrenLength() - 1);
    }

    public List<Expression> getElements() {
        return elements;
    }
}
