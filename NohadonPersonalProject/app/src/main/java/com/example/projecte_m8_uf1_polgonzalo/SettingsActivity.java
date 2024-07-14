package com.example.projecte_m8_uf1_polgonzalo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {
    public static int TextSize=20;
    public static TextView tv;
    public  Button buttonMax;
    public  Button buttonMin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        setTextView();
        setOnClickListeners();
    }
    public void setOnClickListeners(){
        buttonMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumSize();
            }
        });
        buttonMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minusSize();
            }
        });
    }
    public void setTextView(){
        tv = findViewById(R.id.sizeText);
        buttonMax=findViewById(R.id.SumSizeBut);
        buttonMin=findViewById(R.id.MinSizeBut);
    }
    public static void updateText(){
        tv.setText(String.valueOf("The current size of the text is: "+TextSize));
    }
    public static void sumSize(){
        TextSize++;
        updateText();
    }
    public static void minusSize(){
        TextSize--;
        updateText();
    }
}