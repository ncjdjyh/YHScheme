package com.jyh.yhscheme.util;

import com.jyh.yhscheme.Charset;
import com.jyh.yhscheme.core.Environment;

import java.util.HashMap;
import java.util.Map;

public class EnvUtil {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/8 18:31
     * @Description:
     */

    //值和求值方法名的map
    public static Map<String, String> keywordSet;
    public static Map<String, String> builtMap;

    static {
        keywordSet = new HashMap<>();
        builtMap = new HashMap<>();

        keywordSet.put(Charset.DEF, "evalDef");
        keywordSet.put(Charset.IF, "evalIf");
        keywordSet.put(Charset.LAMBDA, "evalLambda");
        keywordSet.put(Charset.LET, "evalLet");
        keywordSet.put(Charset.Begin, "evalBegin");

        builtMap.put(Charset.ADD, "add");
        builtMap.put(Charset.SUB, "sub");
        builtMap.put(Charset.MUL, "mul");
        builtMap.put(Charset.DIV, "div");
        builtMap.put(Charset.GT, "gt");
        builtMap.put(Charset.LT, "lt");
        builtMap.put(Charset.EQ, "eq");
        builtMap.put(Charset.PRINT, "print");
    }

    public static Environment initEnv() {
        return new Environment(null);
    }
}
