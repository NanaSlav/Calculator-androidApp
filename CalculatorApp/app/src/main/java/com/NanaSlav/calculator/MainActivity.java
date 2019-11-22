package com.NanaSlav.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static Expression ex;
    static {
        ex = new Expression(" ");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addSymbol(char symbol) {
        char lastSymbol = ex.str.charAt(ex.str.length() - 1);
        if (Character.isDigit(lastSymbol)) {
            if (symbol == '(') {
                ex.str += "*" + symbol;
            } else {
                if (symbol == '.') {
                    int index = ex.str.lastIndexOf('.') + 1;
                    while ((index < ex.str.length() - 1) && Character.isDigit(ex.str.charAt(index))) {
                        index++;
                    }
                    if (index != ex.str.length() - 1) {
                        ex.str += symbol;
                    }
                } else {
                    ex.str += symbol;
                }

            }
        } else {
            switch (lastSymbol) {
                case '.':
                    if (Character.isDigit(symbol)) {
                        ex.str += symbol;
                    } else {
                        if (symbol == '(') {
                            ex.str += "0*" + symbol;
                        } else {
                            if (symbol != '.') {
                                ex.str += ")" + symbol;
                            }
                        }
                    }
                    break;
                case ')':
                    if (Character.isDigit(symbol)) {
                        ex.str += "*" + symbol;
                    } else {
                        switch (symbol) {
                            case '.':
                                ex.str += "*0" + symbol;
                                break;
                            case '(':
                                ex.str += "*" + symbol;
                                break;
                            default:
                                ex.str = ex.str.substring(0, ex.str.length() - 1);
                                ex.str += symbol;
                        }
                    }

                    break;
                case '(':
                    if (Character.isDigit(symbol)) {
                        ex.str += symbol;
                    } else {
                        if (symbol == '.') {
                            ex.str += "0" +symbol;
                        } else {
                            if (symbol == ')') {
                                ex.str += symbol;
                            }
                        }
                    }
                    break;
                default: // It will be operators (+ - * /)
                    if (Character.isDigit(symbol)) {
                        ex.str += symbol;
                    } else {
                        switch (symbol) {
                            case '.':
                                ex.str += "0" + symbol;
                                break;
                            case '(':
                                ex.str += symbol;
                                break;
                            case ')':
                                ex.str += "(";
                            default:
                                ex.str = ex.str.substring(0,ex.str.length() - 1);
                                ex.str += symbol;

                        }
                    }



            }
        }
        TextView text = (TextView) findViewById(R.id.display);
        text.setText(ex.str);
    }

    public void btnOne(View view) {
        addSymbol('1');
    }

    public void btnTwo(View view) {
        addSymbol('2');
    }

    public void btnThree(View view) {
        addSymbol('3');
    }

    public void btnFour(View view) {
        addSymbol('4');
    }

    public void btnFive(View view) {
        addSymbol('5');
    }

    public void btnSix(View view) {
        addSymbol('6');
    }

    public void btnSeven (View view) {
        addSymbol('7');
    }

    public void btnEight (View view) {
        addSymbol('8');
    }

    public void btnNine (View view) {
        addSymbol('9');
    }

    public void btnZero (View view) {
        addSymbol('0');
    }

    public void btnPlus (View view) {
        addSymbol('+');
    }

    public void btnMinus (View view) {
        addSymbol('-');
    }

    public void btnMult (View view) {
        addSymbol('*');
    }

    public void btnDiv (View view) {
        addSymbol('/');
    }

    public void btnOBracket (View view) {
        addSymbol('(');
    }

    public void btnCBracket (View view) {
        addSymbol(')');
    }

    public void btnDot (View view) {
        addSymbol('.');
    }

}
