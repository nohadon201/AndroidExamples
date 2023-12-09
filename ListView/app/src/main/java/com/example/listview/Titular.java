package com.example.listview;

import java.io.Serializable;

public class Titular implements Serializable {
    private String titol;
    private String subtitol;

    public Titular(String titol, String subtitol) {
        this.titol = titol;
        this.subtitol = subtitol;
    }

    public String getTitulo() {
        return titol;
    }

    public String getSubtitulo() {
        return subtitol;
    }
    public void setTitulo(String titulo){this.titol=titulo;};
    public void setSubtitulo(String subtitulo){this.subtitol=subtitulo;}

}
