package com.NanaSlav;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

public class MyTests {
    public void testExpression() {
        Expression ex1 = new Expression("(125-14*3)/2-14");
        Expression ex2 = new Expression("(2*(3-1))+4");
        Expression ex3 = new Expression("0.25+1+7/2");
        try {
            assertEquals(27.5, Calculator.calculate(ex1), 0.0001);
            assertEquals(8, Calculator.calculate(ex2), 0.0001);
            assertEquals(4.75, Calculator.calculate(ex3), 0.0001);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }




    }
}






