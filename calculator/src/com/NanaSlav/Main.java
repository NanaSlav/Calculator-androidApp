package com.NanaSlav;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        MyTests tests = new MyTests();
        tests.testExpression();
    }
}

/*
    1: <
    2: >
    3: =
    4: ?
    5: Exit
 */

class Calculator {
    private static Stack<String> stack;
    static Map<String, Map<String, Integer> > table = new HashMap<>(); // make it private
        static {
            Map<String, Integer> line1 = new HashMap<>();
                line1.put("+", 1);
                line1.put("*", 1);
                line1.put("(", 1);
                line1.put(")", 4);
                line1.put("", 5);
            Map<String, Integer> line2 = new HashMap<>();
                line2.put("+", 2);
                line2.put("*", 1);
                line2.put("(", 1);
                line2.put(")", 2);
                line2.put("", 2);
            Map<String, Integer> line3 = new HashMap<>();
                line3.put("+", 2);
                line3.put("*", 2);
                line3.put("(", 1);
                line3.put(")", 2);
                line3.put("", 2);
            Map<String, Integer> line4 = new HashMap<>();
                line4.put("+", 1);
                line4.put("*", 1);
                line4.put("(", 1);
                line4.put(")", 3);
                line4.put("", 4);
            Map<String, Integer> line5 = new HashMap<>();
                line5.put("+", 2);
                line5.put("*", 2);
                line5.put("(", 4);
                line5.put(")", 2);
                line5.put("", 2);
            table.put("", line1);
            table.put("+", line2);
            table.put("*", line3);
            table.put("(", line4);
            table.put(")", line5);
        }


    public static double calculate(String expression) {
        stack.push("");

        return 0;
    }
}

class Expression {
    String str;
    public Expression(String expression) {
        this.str = expression;
    }
    public String getNext() {
        String ret = "";
        if (this.str.length() != 0) {
            if(this.str.startsWith("(")) {
                ret = this.str.substring(0,1);
                this.str = this.str.substring(1);
            } else {
                if (this.str.startsWith(")")) {
                    this.str = this.str.substring(1);
                    if (Character.isDigit(this.str.charAt(0))) {
                        ret = "error";
                    } else {
                            ret = this.str.substring(0,1);
                            if (this.str.charAt(0) != ')') {
                                this.str = this.str.substring(1);
                            }
                    }
                    return ret;
                }
                if (this.str.charAt(0) == '-' && Character.isDigit(this.str.charAt(1))) {
                    ret += this.str.substring(0,1);
                    this.str = this.str.substring(1);
                }
                while(Character.isDigit(this.str.charAt(0)) && this.str.length() != 0) {
                    if (this.str.length() != 1) {
                        ret += this.str.substring(0,1);
                        this.str = this.str.substring(1);
                    } else {
                        ret += this.str;
                        this.str = "";
                        return ret;
                    }
                }
                if (ret.isEmpty()) {
                    ret = "error";
                } else {
                    if (this.str.length() > 0) {
                        ret += this.str.substring(0,1);
                        if (this.str.charAt(0) != ')') {
                            this.str = this.str.substring(1);
                        }
                    }

                }
            }
        }

        return ret;

    }
}
