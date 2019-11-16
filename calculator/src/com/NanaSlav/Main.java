package com.NanaSlav;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        // System.out.println("Enter your expression");
        // String expression = in.nextLine();
        // System.out.println("Answer: " + Calculator.calculate(expression));
        System.out.println(Calculator.table.get("+").get("+"));
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
    Stack<String> stack;
    static Map<String, Map<String, Integer> > table = new HashMap<>();
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
        return 0;
    }
}
