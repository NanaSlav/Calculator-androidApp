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
        Expression ex = new Expression("5/(4-3)");
        try {
            Calculator.calculate(ex);
        } catch (Exception exception ) {
            System.out.println(exception.getMessage());
        }


          // in console realization you need to check input string.
    }
}

/*
        Precedence table:

        +  |  *  |  (  |  )  |  ""
       ----------------------------
  "" |  <  |  <  |  <  |  ?  | exit
       ----------------------------
   + |  >  |  <  |  <  |  >  |  >
       -----------------------------
   * |  >  |  >  |  <  |  >  |  >
       -----------------------------
   ( |  <  |  <  |  <  |  =  |  ?
       -----------------------------
   ) |  >  |  >  |  ?  |  >  |  >

    codes of operations:
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



    public static Double calculate (Expression expression) throws Exception {
        Stack<String> stack = new Stack<>();
        stack.push("");
        String symbols ="";
        Double retNum = (double) 0;
        while (!(expression.str.isEmpty()) || !symbols.isEmpty()) {
            if (symbols.isEmpty()) {
                symbols = expression.getNext();
            }
            String lastStackItem = stack.peek();
            String stackSymbol = "";
            boolean isLastSymbolDigit = Character.isDigit(symbols.charAt(symbols.length() - 1));
            if (lastStackItem.isEmpty()) {
                stackSymbol = lastStackItem;
            } else {
                stackSymbol = lastStackItem.substring(lastStackItem.length() - 1);
            }
            int operation = 0;
            if (!isLastSymbolDigit) {
                operation = table.get(stackSymbol).get(symbols.substring(symbols.length() - 1));
            } else {
                operation = table.get(stackSymbol).get("");
            }

            switch (operation) {
                case 1:
                    stack.push(symbols);
                    symbols = "";
                    break;
                case 2:
                    String stackItem = stack.pop();
                    String triple = "";
                    if (isLastSymbolDigit) {
                        triple = stackItem + symbols;
                    } else {
                        triple = stackItem + symbols.substring(0, symbols.length() - 1);
                    }
                    double result = calculateTriple(triple);
                    if (isLastSymbolDigit) {
                        symbols = String.valueOf(result);
                    } else {
                        symbols = result + symbols.substring(symbols.length() - 1);
                    }
                    break;
                case 3:
                    stack.pop();
                    symbols = symbols.substring(0, symbols.length() - 1);
                    try {
                        symbols += expression.getNext();
                    } catch (Exception exception) {

                    }


                    break;
                case 4:
                    throw new Exception("Something wrong with brackets");
                case 5:
                    retNum = Double.valueOf(symbols);
                    symbols = "";
                    break;
            }
        }
        return retNum;
    }

    private static double calculateTriple(String triple) {
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
                try {
                    if (secondNum == 0) {
                        throw new Exception("Division by zero");
                    }
                    retNum = firstNum / secondNum;
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
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

    // returns symbols to analyze
    public String getNext() {
        String ret = "";
        if (this.str.length() != 0) {
            if(this.str.startsWith("(")) {
                ret = this.str.substring(0,1);
                this.str = this.str.substring(1);
            } else {
                if (this.str.startsWith(")")) {
                    this.str = this.str.substring(1);
                    if (Character.isDigit(this.str.charAt(0)) || this.str.charAt(0) == '.') {
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
                while(((Character.isDigit(this.str.charAt(0))) || (this.str.charAt(0) == '.')) && this.str.length() != 0) {
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
