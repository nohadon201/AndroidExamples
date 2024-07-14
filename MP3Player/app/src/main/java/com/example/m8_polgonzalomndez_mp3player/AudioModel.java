package com.example.m8_polgonzalomndez_mp3player;

import java.io.Serializable;
import java.nio.file.Path;

public class AudioModel implements Serializable {
    private String Path;
    private String Title;
    private String Duration;
    public AudioModel(String path, String Title, String Duration){
        this.Path = path;
        this.Title = Title;
        this.Duration = Duration;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }
}
