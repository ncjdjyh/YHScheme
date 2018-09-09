package com.jyh.yhscheme.core;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/8 15:35
     * @Description: 求值环境
     */
    private Environment parentEnv;
    private Map<String, Object> table;

    public Environment(Environment parentEnv) {
        this.parentEnv = parentEnv;
        table = new HashMap<>();
    }

    public Environment getParentEnv() {
        return parentEnv;
    }

    public Map<String, Object> getTable() {
        return table;
    }

    public Object findVariable(String key) {
        /**
         * 功能描述: 找变量绑定的值, 本环境找不到就到父环境找
         * @param: [key]
         * @return: java.lang.Object
         * @auther: ncjdjyh
         * @date: 2018/9/9
         */
        if (this.table.containsKey(key))
            return this.table.get(key);
        else {
            if (this.parentEnv == null) return null;
            return this.parentEnv.findVariable(key);
        }
    }

    public void extendEnvironment(String key, Object value) {
        this.table.put(key, value);
    }
}
