package com.jyh.yhscheme;

import java.util.List;

public class Built {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/8 22:13
     * @Description: 内置基本过程
     * @param args
     */

    public static Integer add(List<Object> args) {
        int result = 0;
        for (Object arg: args) {
            result += (int) arg;
        }
        return result;
    }

    public static Integer sub(List<Object> args) {
        int result = 0;
        for (Object arg: args) {
            result -= (int) arg;
        }
        return result;
    }

    public static Integer mul(List<Object> args) {
        int result = 1;
        for (Object arg: args) {
            result *= (int) arg;
        }
        return result;
    }

    public static Integer div(List<Object> args) {
        int result = (int) args.get(0);
        for (int i = 1; i < args.size(); i++) {
            result /= (int) args.get(i);
        }
        return result;
    }
}
