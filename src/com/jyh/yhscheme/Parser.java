package com.jyh.yhscheme;

public class Parser {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/7 09:23
     * @Description:
     */

    public static String[] getTokens(String src) {
        /**
         * 功能描述:获取token
         * @param: [src]
         * @return: java.lang.String[]
         * @auther: ncjdjyh
         * @date: 2018/9/8
         */
        src = src.replaceAll("\\(", " ( ").replaceAll("\\)", " ) ");
        String[] tokens = src.trim().split("\\s+");
        return tokens;
    }

    public static Expression parse(String[] tokens) {
        /**
         * 功能描述: 将token转化为ast
         * @param: [tokens]
         * @return: com.jyh.yhscheme.Expression
         * @auther: ncjdjyh
         * @date: 2018/9/8
         */
        Expression root = new Expression(null, "root");
        Expression parent = null;
        for (String t: tokens) {
            Expression node = new Expression(parent, t);
            if (parent == null) {
                parent = node;
                root = parent;
            } else {
                parent.addChild(node);
            }
            switch (t) {
                case Charset.START_TOKEN: {
                    parent = node;
                    break;
                }
                case Charset.END_TOKEN: {
                    parent = parent.getParent();
                    break;
                }
            }
        }
        return root;
    }
}
