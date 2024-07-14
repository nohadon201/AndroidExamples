package com.example.m8_polgonzalomndez_mp3player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.FileObserver;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    RecyclerView recyclerView;
    TextView NoSongText;
    ArrayList<AudioModel> audioModelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        NoSongText = findViewById(R.id.no_songs_text);

        if(!checkPermission()){
            requestPermission();
            return;
        }
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 ";
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection, selection, null,null);
        while(cursor.moveToNext()){
            AudioModel example = new AudioModel(cursor.getString(1),cursor.getString(0),cursor.getString(2));
            if(new File(example.getPath()).exists()){
                audioModelArrayList.add(example);
            }
        }
        if(audioModelArrayList.size()==0){
            NoSongText.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MusicListAdapter(audioModelArrayList, getApplicationContext()));
        }
        imageView = findViewById(R.id.menuRecordBtn);
        imageView.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), RecorderActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            System.out.println("D:<");
            MainActivity.this.startActivity(intent);
        });
    }
    public boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }
    public void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this, "READ PERMISSION IS REQUIRED. PLEASE TO THE WELL WORK OF THE APPLICATION GRANT THE NECESARY PERMISIONS.", Toast.LENGTH_SHORT);
        }else{
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(recyclerView!=null){
            recyclerView.setAdapter(new MusicListAdapter(audioModelArrayList,getApplicationContext()));
        }
    }
}