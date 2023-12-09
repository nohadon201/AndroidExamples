package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class ActivityChild extends AppCompatActivity implements Serializable {

    private String titulo;
    private String subtitulo;

    public ActivityChild(String tit, String sub) {
        titulo=tit;
        subtitulo=sub;
    }

    public ActivityChild() {
    }

    public String getTitulo(){
        return titulo;
    }

    public String getSubtitulo(){
        return subtitulo;
    }
}