package com.jyh.yhscheme.type;

import com.jyh.yhscheme.Expression;
import com.jyh.yhscheme.core.Environment;

import java.util.ArrayList;
import java.util.List;

public class Procedure {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/9 15:17
     * @Description:
     */
    private List<String> params;
    private List<Expression> body;
    private Environment currentEnv;

    public Environment getCurrentEnv() {
        return currentEnv;
    }

    public List<String> getParams() {
        return params;
    }

    public List<Expression> getBody() {
        return body;
    }

    //将过程体与此时的环境打包
    public Procedure(List<String> params, List<Expression> body, Environment env) {
        this.params = params;
        this.body = body;
        this.currentEnv = env;
    }
}
