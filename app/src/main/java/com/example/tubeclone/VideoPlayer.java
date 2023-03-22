package com.example.tubeclone;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class VideoPlayer extends AppCompatActivity {
    private YouTubePlayerFragment youtubeFragment;
    private static final String API_KEY = "AIzaSyCDvmWGhROai25Djm8Z0ZsQDi0VA0XSGhM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        String videoId = getIntent().getStringExtra("VIDEO_ID");
        youtubeFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
        youtubeFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {

                if(!b) {
                    player.loadVideo(videoId);

                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    player.setShowFullscreenButton(true);
                    player.setManageAudioFocus(true);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }


        });
    }
}