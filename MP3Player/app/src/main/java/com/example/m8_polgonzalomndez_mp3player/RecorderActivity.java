package com.example.m8_polgonzalomndez_mp3player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

public class RecorderActivity extends AppCompatActivity {
    private String PATH_BASE;
    ImageView recordStop,goBackToList;
    MediaRecorder mediaRecorder = MyMediaRecorder.getInstance();
    private boolean recording;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);
        recording=false;
        setResources();
    }

    private void setResources() {
        goBackToList = findViewById(R.id.listAudiosBtn);
        recordStop = findViewById(R.id.MicroButton);
        goBackToList.setOnClickListener(view -> {
            if (recording){
                recording=false;
                recordStop.setImageResource(R.drawable.micronorecording);
                stopRecording();
            }
            Intent intent = new Intent(RecorderActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            System.out.println("D:<");
            RecorderActivity.this.startActivity(intent);
        });
        recordStop.setOnClickListener(view -> {
            if (recording){
                recording=false;
                recordStop.setImageResource(R.drawable.micronorecording);
                stopRecording();
            }else if(checkPermission()){
                recording=true;
                recordStop.setImageResource(R.drawable.micro);
                startRecording();
            }
        });
    }
    private void stopRecording(){
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }
    private void startRecording(){
        mediaRecorder= new MediaRecorder();
        mediaRecorder.reset();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        PATH_BASE  = getApplicationContext().getExternalFilesDir("/").getAbsolutePath();
        System.out.println(PATH_BASE+"/filename.mp3");
        mediaRecorder.setOutputFile(PATH_BASE+"/filename.mp3");

        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try{
            mediaRecorder.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
        mediaRecorder.start();
    }
    private boolean checkPermission(){
        if(ActivityCompat.checkSelfPermission(RecorderActivity.this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            ActivityCompat.requestPermissions(RecorderActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},21);
            return false;
        }
    }
}