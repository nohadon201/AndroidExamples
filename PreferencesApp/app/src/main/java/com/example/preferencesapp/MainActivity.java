package com.example.preferencesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    int a = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.preferences);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a++;
                Intent i = new Intent(MainActivity.this, preferences.class);
                Bundle b = new Bundle();

                b.putSerializable("count", a);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}