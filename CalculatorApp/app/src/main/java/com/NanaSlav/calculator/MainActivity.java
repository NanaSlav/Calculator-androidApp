package com.NanaSlav.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void calculate(View view) {
        Expression ex = new Expression("2+2*2");
        double res = (double) 0;
        try {
            res = Calculator.calculate(ex);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        TextView text = (TextView) findViewById(R.id.txt);
        text.setTextSize(40);
        text.setText(String.valueOf(res));
    }
}
