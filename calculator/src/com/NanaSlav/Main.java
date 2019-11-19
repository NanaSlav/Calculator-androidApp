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
        // Expression ex2 = new Expression("(2*(3-1))+4");
        // Calculator.calculate(ex2);
        System.out.println(Calculator.calculateTriple("12.5+1"));
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
    static Map<String, Map<String, Integer> > table = new HashMap<>(); // make it private
        static {
            Map<String, Integer> line1 = new HashMap<>();
                line1.put("+", 1);
                line1.put("-", 1);
                line1.put("*", 1);
                line1.put("/", 1);
                line1.put("(", 1);
                line1.put(")", 4);
                line1.put("", 5);
            Map<String, Integer> line2 = new HashMap<>();
                line2.put("+", 2);
                line2.put("-", 2);
                line2.put("*", 1);
                line2.put("/", 1);
                line2.put("(", 1);
                line2.put(")", 2);
                line2.put("", 2);
            Map<String, Integer> line3 = new HashMap<>();
                line3.put("+", 2);
                line3.put("-", 2);
                line3.put("*", 2);
                line3.put("/", 2);
                line3.put("(", 1);
                line3.put(")", 2);
                line3.put("", 2);
            Map<String, Integer> line4 = new HashMap<>();
                line4.put("+", 1);
                line4.put("-", 1);
                line4.put("*", 1);
                line4.put("/", 1);
                line4.put("(", 1);
                line4.put(")", 3);
                line4.put("", 4);
            Map<String, Integer> line5 = new HashMap<>();
                line5.put("+", 2);
                line5.put("-", 2);
                line5.put("*", 2);
                line5.put("/", 2);
                line5.put("(", 4);
                line5.put(")", 2);
                line5.put("", 2);
            table.put("", line1);
            table.put("+", line2);
            table.put("-", line2);
            table.put("*", line3);
            table.put("/", line3);
            table.put("(", line4);
            table.put(")", line5);
        }

    // TODO: solve problem with ")"

    public static double calculate(Expression expression) {
        Stack<String> stack = new Stack<>();
        stack.push("");
        String symbols ="";
        while (!expression.str.isEmpty()) {
            symbols = expression.getNext();
            if (!Character.isDigit(symbols.charAt(symbols.length() - 1))) {
                // table(stack.peek(), symbols.substring(symbols.length()-2));
                String lastStackItem = stack.peek();
                String stackSymbol = "";
                if (lastStackItem.isEmpty()) {
                    stackSymbol = lastStackItem;
                } else {
                    stackSymbol = lastStackItem.substring(lastStackItem.length() - 1);
                }
                int operation = table.get(stackSymbol).get(symbols.substring(symbols.length() - 1));
                switch (operation) {
                    case 1:
                        stack.push(symbols);
                        System.out.println("<");
                        break;
                    case 2:
                        // TODO: add realization of >
                        stack.pop();

                        System.out.println(">");
                        break;
                    case 3:
                        // TODO: add realization of =
                        stack.pop();
                        System.out.println("=");
                        break;
                    case 4:
                        // TODO: add exception instead
                        System.out.println("Error");
                        break;
                    case 5:
                        // TODO: add realization of exit
                        System.out.println("exit");
                        break;
                }
            }
        }


        return 0;
    }
    //private static double calculateTriple(String triple) {
    public static double calculateTriple(String triple) {
        int i = 0;
        while (Character.isDigit(triple.charAt(i)) || triple.charAt(i) == '.') {
            i++;
        }
        Double firstNum = Double.valueOf(triple.substring(0, i));
        Double secondNum = Double.valueOf(triple.substring(i + 1));
        double retNum = 0;
        char operator = triple.charAt(i);

        switch (operator) {
            case '+':
                retNum = firstNum + secondNum;
                break;
            case '-':
                retNum = firstNum - secondNum;
                break;
            case '*':
                retNum = firstNum * secondNum;
                break;
            case '/':
                retNum = firstNum / secondNum;
                break;
        }
        return retNum;
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
