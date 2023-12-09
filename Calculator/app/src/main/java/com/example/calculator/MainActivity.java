package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public double memory;
    public boolean reset=true;
    private String signed="";
    private float Memory = 0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView calview = findViewById(R.id.cal_view);
        Button b1 = (Button) findViewById(R.id.one);
        Button b2 = (Button) findViewById(R.id.two);
        Button b3 = (Button) findViewById(R.id.three);
        Button b4 = (Button) findViewById(R.id.four);
        Button b5 = (Button) findViewById(R.id.five);
        Button b6 = (Button) findViewById(R.id.six);
        Button b7 = (Button) findViewById(R.id.seven);
        Button b8 = (Button) findViewById(R.id.eight);
        Button b9 = (Button) findViewById(R.id.nine);
        Button b0 = (Button) findViewById(R.id.zero);
        Button b_div = (Button) findViewById(R.id.divide);
        Button b_mul = (Button) findViewById(R.id.multi);
        Button b_plus = (Button) findViewById(R.id.plus);
        Button b_minus = (Button) findViewById(R.id.minus);
        Button equal = (Button) findViewById(R.id.equal);
        Button m_plus = (Button) findViewById(R.id.M_plus);
        Button m_minus = (Button) findViewById(R.id.M_min);
        Button m_recover = (Button) findViewById(R.id.MR);
        Button m_clear = (Button) findViewById(R.id.MC);
        Button ca = (Button) findViewById(R.id.CA);
        Button del = (Button) findViewById(R.id.del);
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) findViewById(view.getId());
                if(view.getId() != R.id.del && view.getId() != R.id.CA && view.getId() != R.id.MC && view.getId() != R.id.MR && view.getId() != R.id.M_plus && view.getId() != R.id.M_min && view.getId() != R.id.equal){
                    if(view.getId() != R.id.multi && view.getId() != R.id.divide && view.getId() != R.id.plus && view.getId() != R.id.minus){
                        numberButton(b,calview);
                    }else if(signed.equals("")){
                        if(reset){
                            reset=false;
                        }
                        CharSequence t = TextUtils.concat(calview.getText(),b.getText());
                        calview.setText(t);
                        signed=""+b.getText();
                    }
                }else{
                    if(view.getId() == R.id.MC){
                        Memory=0;
                    } else if(view.getId() == R.id.MR){
                        if(signed.equals("")){
                            calview.setText(String.valueOf(Memory));
                        }else {
                            String[] arr = calview.getText().toString().split("[-+x%]");
                            if(arr.length==1) {
                                calview.setText(TextUtils.concat(calview.getText(), String.valueOf(Memory)));
                            }
                        }
                    } else if(view.getId() == R.id.M_plus && !calview.getText().toString().contains("+") && !calview.getText().toString().contains("-") && !calview.getText().toString().contains("%") && !calview.getText().toString().contains("x")){
                        operationMemory("+",Float.parseFloat(calview.getText().toString()),calview);
                    }else if(view.getId() == R.id.M_min && !calview.getText().toString().contains("+") && !calview.getText().toString().contains("-") && !calview.getText().toString().contains("%") && !calview.getText().toString().contains("x")){
                        operationMemory("-",Float.parseFloat(calview.getText().toString()),calview);
                    }else if(view.getId() == R.id.equal){
                        equalfun(calview);
                    }else if(view.getId() == R.id.del){
                        if(calview.getText().toString().substring(0,calview.getText().toString().length()-1).equals("")){
                            calview.setText("0");
                            signed="";
                            reset=true;
                        }else {
                            calview.setText(calview.getText().toString().substring(0, calview.getText().toString().length() - 1));
                        }
                    }else if(view.getId() == R.id.CA){
                        calview.setText("0");
                        signed="";
                        reset=true;
                    }
                }
            }
        };
        b1.setOnClickListener(ocl);
        b2.setOnClickListener(ocl);
        b3.setOnClickListener(ocl);
        b4.setOnClickListener(ocl);
        b5.setOnClickListener(ocl);
        b6.setOnClickListener(ocl);
        b7.setOnClickListener(ocl);
        b8.setOnClickListener(ocl);
        b9.setOnClickListener(ocl);
        b0.setOnClickListener(ocl);
        b_div.setOnClickListener(ocl);
        b_mul.setOnClickListener(ocl);
        b_plus.setOnClickListener(ocl);
        b_minus.setOnClickListener(ocl);
        equal.setOnClickListener(ocl);
        m_plus.setOnClickListener(ocl);
        m_minus.setOnClickListener(ocl);
        m_recover.setOnClickListener(ocl);
        m_clear.setOnClickListener(ocl);
        del.setOnClickListener(ocl);
        ca.setOnClickListener(ocl);
    }
    public void operationMemory(String sign, float number,TextView tv){
        if(sign.equals("+")){
            tv.setText(String.valueOf(Memory+number));
        }else if(sign.equals("-")){
            tv.setText(String.valueOf(Memory-number));
        }
    }
    public void numberButton(Button b, TextView tv){
        CharSequence t;
        if(reset || tv.getText().toString().equals("0.0")){
            t = b.getText();
            reset=false;
        }else{
            t = TextUtils.concat(tv.getText(),b.getText());
        }
        tv.setText(t);
    }
    public void equalfun(TextView tv){
        reset=true;
        if(!this.signed.equals("")){
            String[] arr = tv.getText().toString().split("[-+x%]");
            float f1 = Float.parseFloat(arr[0]);
            float f2 = Float.parseFloat(arr[1]);
            switch (signed){
                case "+":
                    tv.setText(String.valueOf(f1+f2));
                    Memory=f1+f2;
                    break;
                case "-":
                    tv.setText(String.valueOf(f1-f2));
                    Memory=f1-f2;
                    break;
                case "x":
                    tv.setText(String.valueOf(f1*f2));
                    Memory=f1*f2;
                    break;
                case "%":
                    tv.setText(String.valueOf(f1/f2));
                    Memory=f1/f2;
                    break;
                default:
                    Log.e("CalculatorDataFormating","The Sign is incorrect");
                    break;
            }
        }else{
            Memory=Float.parseFloat(tv.getText().toString());
        }
        signed="";
    }
}