package com.example.helloword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public int a = 0;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * DEFINICIÓ DE VARIABLES
         */
        //BOTONS
        Button btn = findViewById(R.id.btn_1);
        Button color = findViewById(R.id.Color);
        Button minus = findViewById(R.id.minus);
        Button plus = findViewById(R.id.plus);
        Button btn2 = findViewById(R.id.btn_2);
        Button btn3 = findViewById(R.id.btn_3);
        //TEXTVIEWS
        TextView tv = findViewById(R.id.txt_v_1);
        TextView tv2 = findViewById(R.id.text_num);
        //setting the init configurations
        tv.setVisibility(TextView.INVISIBLE);
        tv.setTextSize(10f);
        btn.setText("Apareix");
        tv2.setText(getString(R.string.text_Num)+" "+a+" vegades");
        /**
         * ON-CLICK LISTENERS
         */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv.getVisibility()==TextView.INVISIBLE){
                    tv.setVisibility(TextView.VISIBLE);
                }else{
                    tv.setVisibility(TextView.INVISIBLE);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a++;
                tv2.setText(getString(R.string.text_Num)+" "+a+" vegades");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a--;
                tv2.setText(getString(R.string.text_Num)+" "+a+" vegades");
            }
        });
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv.getCurrentTextColor()==Color.WHITE){
                    tv.setTextColor(Color.BLUE);
                }else if(tv.getCurrentTextColor()==Color.BLUE){
                    tv.setTextColor(Color.CYAN);
                }else if(tv.getCurrentTextColor()==Color.CYAN){
                    tv.setTextColor(Color.MAGENTA);
                }else if(tv.getCurrentTextColor()==Color.MAGENTA){
                    tv.setTextColor(Color.GREEN);
                }else if(tv.getCurrentTextColor()==Color.GREEN){
                    tv.setTextColor(Color.YELLOW);
                }else if(tv.getCurrentTextColor()==Color.YELLOW){
                    tv.setTextColor(Color.WHITE);
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv.getTextSize()+0.001<150 && tv.getVisibility()== View.VISIBLE){
                    tv.setTextSize(tv.getTextSize());
                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv.getVisibility()== View.VISIBLE){
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,tv.getTextSize()-10f);
                }
            }
        });
    }
}