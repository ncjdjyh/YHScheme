package com.jyh.yhscheme.util;

public class Charset {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/9/7 10:06
     * @Description:
     */

    public static final String START_TOKEN = "(";
    public static final String END_TOKEN = ")";

    // bool functions
    public static final String AND = "and";
    public static final String OR = "or";
    public static final String NOT = "not";

    // number functions
    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String MUL = "*";
    public static final String DIV = "/";
    public static final String GT = ">";
    public static final String LT = "<";
    public static final String EQ = "=";

    // pair functions
    public static final String CONS = "cons";
    public static final String CAR = "car";
    public static final String CDR = "cdr";
    public static final String LIST = "list";
    public static final String NULL = "null?";

    // string functions
    public static final String STR_EQ = "str=?";

    // builtin helper functions
    public static final String PRINT = "display";

    // builtin keywords
    public static final String DEF = "def";
    public static final String IF = "if";
    public static final String LAMBDA = "lambda";
    public static final String LET = "let";
    public static final String Begin = "begin";

    // builtin literals
    public static final String NIL = "nil";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
}
