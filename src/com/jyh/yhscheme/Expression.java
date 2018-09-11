package com.jyh.yhscheme;

import java.util.ArrayList;
import java.util.List;

public class Expression {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/7 09:47
     * @Description: ast类
     */

    private Expression parent;
    private List<Expression> children;
    private String value;

    public Expression(Expression parent, String value) {
        this.parent = parent;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(Expression exp) {
        children.add(exp);
    }

    public Expression getParent() {
        return this.parent;
    }

    public List<Expression> getChildren() {
        return this.children;
    }

    public List<Expression> getChildrenExceptBracket () {
        return this.children.subList(0, children.size() - 1);
    }

    public Expression getFirstSubExpression() {
        return this.children.get(1);
    }

    public int getChildrenLength() {
        return this.children.size();
    }

    public String getValue() {
        return this.value;
    }

    public String getOperator() {
        return this.children.get(0).getValue();
    }

    public List<Expression> getOperands() {
        int length = this.children.size();
        return this.children.subList(1, length - 1);
    }

    @Override
    public String toString() {
        if (0 == children.size()) {
            return this.value;
        } else {
            StringBuffer displayBuffer = new StringBuffer(this.value + " ");
            for (Expression child : children) {
                displayBuffer.append(child.toString() + " ");
            }
            return displayBuffer.toString();
        }
    }
}
