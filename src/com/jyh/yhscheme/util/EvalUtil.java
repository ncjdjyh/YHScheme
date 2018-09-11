package com.jyh.yhscheme.util;

import java.util.HashSet;
import java.util.Set;

public class EvalUtil {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/8 18:31
     * @Description:
     */
    public static Set<String> keywordSet;
    public static Set<String> builtSet;

    static {
        keywordSet = new HashSet<>();
        builtSet = new HashSet<>();

        keywordSet.add(Charset.DEF);
        keywordSet.add(Charset.IF);
        keywordSet.add(Charset.LAMBDA);
        keywordSet.add(Charset.LET);
        keywordSet.add(Charset.Begin);

        builtSet.add(Charset.ADD);
        builtSet.add(Charset.SUB);
        builtSet.add(Charset.MUL);
        builtSet.add(Charset.DIV);
        builtSet.add(Charset.GT);
        builtSet.add(Charset.LT);
        builtSet.add(Charset.EQ);
        builtSet.add(Charset.PRINT);
    }
}
