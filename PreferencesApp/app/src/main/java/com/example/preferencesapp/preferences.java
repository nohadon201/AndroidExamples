package com.example.preferencesapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import java.io.Serializable;

public class preferences extends PreferenceActivity {
    private int count ;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Bundle bundle = this.getIntent().getExtras();
        count = (int) bundle.getSerializable("count");
        //Button b =
    }
}
