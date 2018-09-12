package com.jyh.yhscheme.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment implements Cloneable {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/8 15:35
     * @Description: 求值环境
     */
    private Environment parentEnv;
    private HashMap<String, Object> table;

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

    /*找变量绑定的值, 本环境找不到就到父环境找*/
    public Object findVariable(String key) {
        if (this.table.containsKey(key))
            return this.table.get(key);
        else {
            if (this.parentEnv == null) return null;
            return this.parentEnv.findVariable(key);
        }
    }

    /*扩充求值环境*/
    public void extendEnvironment(String key, Object value) {
        this.table.put(key, value);
    }

    public void extendEnvironment(List<String> keys, List<Object> values) {
        for (int i = 0; i < keys.size(); i++) {
            extendEnvironment(keys.get(i), values.get(i));
        }
    }

    @Override
    public Environment clone() throws CloneNotSupportedException {
        Environment newEnv = (Environment) super.clone();
        newEnv.table = (HashMap<String, Object>) table.clone();
        return newEnv;
    }

    @Override
    public String toString() {
        StringBuffer displayBuffer = new StringBuffer();
        for (Map.Entry<String, Object> entry : table.entrySet()) {
            displayBuffer.append(entry.getKey() + "->" + entry.getValue() + "   ");
        }
        return displayBuffer.toString();
    }
}
