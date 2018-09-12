package com.jyh.yhscheme.util;

import com.jyh.yhscheme.Expression;

import java.util.ArrayList;
import java.util.List;

public class ExpUtil {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/12 09:22
     * @Description: 获取表达式中参数的工具类
     */

    public static Expression findChild(int index, Expression exp) {
        return exp.getChildren().get(index);
    }

    public static String findChildValue(int index, Expression exp) {
        return exp.getChildren().get(index).getValue();
    }

    public static List<Expression> getChildrenExceptBracket (Expression exp) {
        return findSubExpression(0, exp);
    }

    public static List<Expression> getChildrenExceptBracketAndOperator (Expression exp) {
        return findSubExpression(1, exp);
    }

    public static List<Expression> findSubExpression(int from, Expression exp) {
        return exp.getChildren().subList(from, exp.getChildrenLength() - 1);
    }

    public static List<String> getParams(Expression exp) {
        return getExpParamsValues(getChildrenExceptBracket(exp));
    }

    public static List<String> getParamsExceptOperator(Expression exp) {
        return getExpParamsValues(getChildrenExceptBracketAndOperator(exp));
    }

    private static List<String> getExpParamsValues(List<Expression> expParams) {
        List<String> params = new ArrayList<>();
        for (Expression e : expParams) {
            params.add(e.getValue());
        }
        return params;
    }
}
