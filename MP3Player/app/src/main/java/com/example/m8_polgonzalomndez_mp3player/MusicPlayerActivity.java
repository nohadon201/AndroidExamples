package com.example.m8_polgonzalomndez_mp3player;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {
    public ImageView pausePlay, nextBtn, previousBtn, musicIcon;
    private SeekBar seekBar;
    private TextView titleTV, currentTimeTv, totalTimeTv;
    private ArrayList<AudioModel> songsList;
    private AudioModel audioModel;
    private int x;

    MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        System.out.println("aaaa");
        titleTV = findViewById(R.id.song_title);
        currentTimeTv = findViewById(R.id.currentTime);
        totalTimeTv = findViewById(R.id.totalTime);
        seekBar = findViewById(R.id.seekBar);
        pausePlay = findViewById(R.id.play);
        nextBtn = findViewById(R.id.next);
        previousBtn = findViewById(R.id.previous);
        musicIcon = findViewById(R.id.music_Icon);

        titleTV.setSelected(true);
        try{
            songsList = (ArrayList<AudioModel>) getIntent().getSerializableExtra("LIST");
        }catch (Exception e){
            System.err.println("ª");
        }
        setResourcesWithMusic();

        MusicPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTimeTv.setText(convertToMSS(mediaPlayer.getCurrentPosition()+""));

                    if(mediaPlayer.isPlaying()){
                        pausePlay.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                        musicIcon.setRotation(x++);
                    }else{
                        pausePlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        musicIcon.setRotation(0);
                    }
                }
                new Handler().postDelayed(this, 100);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(mediaPlayer!=null && b){
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setResourcesWithMusic(){
        audioModel = songsList.get(MyMediaPlayer.currentIndex);
        titleTV.setText(audioModel.getTitle());
        totalTimeTv.setText(convertToMSS(audioModel.getDuration()));

        pausePlay.setOnClickListener(v->pausePlay());
        nextBtn.setOnClickListener(v->playNext());
        previousBtn.setOnClickListener(v->playPrevious());
        try {
            playMusic();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void playMusic() throws IOException {
        mediaPlayer.reset();
        mediaPlayer.setDataSource(audioModel.getPath());
        mediaPlayer.prepare();
        mediaPlayer.start();
        seekBar.setProgress(0);
        seekBar.setMax(mediaPlayer.getDuration());
    }
    private void playNext(){
        if(MyMediaPlayer.currentIndex==songsList.size()-1){
            return;
        }
        MyMediaPlayer.currentIndex++;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }
    private void playPrevious(){
        if(MyMediaPlayer.currentIndex==0){
            return;
        }
        MyMediaPlayer.currentIndex--;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }
    private void pausePlay(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else{
            mediaPlayer.start();
        }
    }
    private String convertToMSS(String input){
        Long millis = Long.parseLong(input);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }
}