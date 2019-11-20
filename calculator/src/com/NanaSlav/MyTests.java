package com.NanaSlav;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

public class MyTests {
    public void testExpression() {
        Expression ex1 = new Expression("(125-14*3)/2-14");
        Expression ex2 = new Expression("(2*(3-1))+4");
        Expression ex3 = new Expression("0.25+1+7/2");
        String triple = "25+1.2";

        assertEquals("(",ex1.getNext());
        assertEquals("125-",ex1.getNext());
        assertEquals("14*",ex1.getNext());
        assertEquals("3)",ex1.getNext());
        assertEquals("/",ex1.getNext());
        assertEquals("2-",ex1.getNext());
        assertEquals("14",ex1.getNext());

        assertEquals("(",ex2.getNext());
        assertEquals("2*",ex2.getNext());
        assertEquals("(",ex2.getNext());
        assertEquals("3-",ex2.getNext());
        assertEquals("1)",ex2.getNext());
        assertEquals(")",ex2.getNext());
        assertEquals("+",ex2.getNext());
        assertEquals("4",ex2.getNext());

        assertEquals("0.25+",ex3.getNext());
        assertEquals("1+",ex3.getNext());
        assertEquals("7/",ex3.getNext());
        assertEquals("2",ex3.getNext());

        assertEquals(26.2, Calculator.calculateTriple(triple), 0.0001);

    }
}






