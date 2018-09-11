package com.jyh.yhscheme;

import com.jyh.yhscheme.core.Environment;
import com.jyh.yhscheme.core.Eval;
import com.jyh.yhscheme.util.EnvUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/7 09:22
     * @Description:
     */

    public static void main(String[] args) {
        ReadPrintLoop readPrintLoop = new ReadPrintLoop();
        Thread woker = new Thread(readPrintLoop);
        woker.start();
    }
}

class ReadPrintLoop implements Runnable {
    BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    public void run() {
        Environment rootEnv = EnvUtil.initEnv();
        String src;
        while (true) {
            System.out.print(">> ");
            try {
                src = console.readLine();
                if (!"".equals(src.trim())) {
                    String[] tokens = Parser.getTokens(src);
                    Expression instruct = Parser.parse(tokens);
                    Object val = Eval.eval(instruct, rootEnv);
                    if (val != null) {
                        System.out.println(val);
                    }
                } else {
                    System.err.println("empty cmd !");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
