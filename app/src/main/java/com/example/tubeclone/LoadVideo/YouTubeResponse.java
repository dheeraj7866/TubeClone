package com.example.tubeclone.LoadVideo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YouTubeResponse {

    @SerializedName("items")
    private List<YouTubeVideo> videos;

    public List<YouTubeVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<YouTubeVideo> videos) {
        this.videos = videos;
    }
}
