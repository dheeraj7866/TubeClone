package com.example.tubeclone.LoadVideo;

import com.google.gson.annotations.SerializedName;

public class YouTubeVideo {

    @SerializedName("id")
    private VideoId videoId;

    @SerializedName("snippet")
    private VideoSnippet videoSnippet;

    public VideoId getVideoId() {
        return videoId;
    }

    public void setVideoId(VideoId videoId) {
        this.videoId = videoId;
    }

    public VideoSnippet getVideoSnippet() {
        return videoSnippet;
    }

    public void setVideoSnippet(VideoSnippet videoSnippet) {
        this.videoSnippet = videoSnippet;
    }

    public class VideoId {

        @SerializedName("videoId")
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public class VideoSnippet {

        @SerializedName("title")
        private String title;

        @SerializedName("description")
        private String description;

        @SerializedName("thumbnails")
        private Thumbnails thumbnails;
        private String liveBroadcastContent;
        public String getLiveBroadcastContent() {
            return liveBroadcastContent;
        }

        public void setLiveBroadcastContent(String liveBroadcastContent) {
            this.liveBroadcastContent = liveBroadcastContent;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Thumbnails getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(Thumbnails thumbnails) {
            this.thumbnails = thumbnails;
        }
    }

    public class Thumbnails {

        @SerializedName("medium")
        private Thumbnail medium;

        public Thumbnail getMedium() {
            return medium;
        }

        public void setMedium(Thumbnail medium) {
            this.medium = medium;
        }
    }

    public class Thumbnail {

        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

