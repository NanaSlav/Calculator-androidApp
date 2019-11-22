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

}
