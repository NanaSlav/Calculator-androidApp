package com.NanaSlav.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
                case ' ':
                    if (Character.isDigit(symbol) || symbol == '-' || symbol == '(') {
                        ex.str += symbol;
                    } else {
                        if (symbol == '.') {
                            ex.str += "0.";
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
                                break;
                            case '-':
                                ex.str += "(-";
                                break;
                            default:
                                ex.str = ex.str.substring(0,ex.str.length() - 1);
                                ex.str += symbol;

                        }
                    }
            }
        }

    }

    public void btnClick(View view) {
        Button btn = (Button) view;
        String btnText = btn.getText().toString();
        if (btnText.equalsIgnoreCase("c")) {
            ex.str = " ";
        } else {
            if (btnText.equalsIgnoreCase("del")) {
                if (!ex.str.equals(" ")) {
                    ex.str = ex.str.substring(0,ex.str.length() - 1);
                }
            } else {
                addSymbol(btnText.charAt(0));
            }
        }
        TextView text = findViewById(R.id.display);
        text.setText(ex.str);
    }

    public void calculate (View view) {
        char lastSymbol = ex.str.charAt(ex.str.length() - 1);
        if ( lastSymbol == '+' || lastSymbol == '-' ||
                lastSymbol == '*' || lastSymbol == '/') {
            ex.str = ex.str.substring(0, ex.str.length() - 1);
        }

        closeBrackets();

        try {
            ex.str = ex.str.substring(1);
            Double result = Calculator.calculate(ex);
            ex.str = " " + result;
            // To print integer numbers without .0
            if (result == Math.floor(result)) {
                ex.str = ex.str.substring(0,ex.str.length() - 2);
            }
            TextView text = findViewById(R.id.display);
            text.setText(ex.str);
        } catch (Exception exception) {
            TextView text = findViewById(R.id.display);
            text.setText(exception.getMessage());
        }

    }

    public void closeBrackets() {
        int numOBrackets = 0;
        int numCBrackets = 0;
        for (int i = 0; i < ex.str.length(); i++) {
            if (ex.str.charAt(i) == ')') numCBrackets++;
            if (ex.str.charAt(i) == '(') numOBrackets++;
        }

        if (numCBrackets > numOBrackets) {
            for (int i = numCBrackets - numOBrackets; i > 0; i--) {
                ex.str = " (" + ex.str.substring(1);
            }
        } else {
            if (numOBrackets > numCBrackets) {
                for (int i = numOBrackets - numCBrackets; i > 0; i--) {
                    ex.str += ")";
                }
            }
        }
    }


}
