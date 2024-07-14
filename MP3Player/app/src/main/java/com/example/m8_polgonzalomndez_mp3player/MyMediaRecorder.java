package com.example.m8_polgonzalomndez_mp3player;

import android.media.MediaRecorder;

public class MyMediaRecorder {
    private static MediaRecorder myMediaRecorder;
    public static MediaRecorder getInstance(){
        if(myMediaRecorder==null){
            myMediaRecorder=new MediaRecorder();
        }
        return myMediaRecorder;
    }

}
