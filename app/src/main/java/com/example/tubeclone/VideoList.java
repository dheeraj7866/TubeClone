package com.example.tubeclone;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubeclone.LoadVideo.VideoAdapter;
import com.example.tubeclone.LoadVideo.YouTubeResponse;
import com.example.tubeclone.LoadVideo.YouTubeService;
import com.example.tubeclone.LoadVideo.YouTubeVideo;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoList extends AppCompatActivity {

    VideoAdapter videoAdapter;
    ProgressDialog progressDialog;

    List<YouTubeVideo> videos=new ArrayList<>();

    private static final String apiKey = BuildConfig.API_KEY;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videolist);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.show();

        String CHANNEL_ID = getIntent().getStringExtra("url");
        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create YouTubeService instance
        YouTubeService youTubeService = retrofit.create(YouTubeService.class);

        // Call the API endpoint to search for videos
        Call<YouTubeResponse> call = youTubeService.searchVideos(apiKey, CHANNEL_ID, "snippet", "viewCount", 50,"live","video");
        call.enqueue(new Callback<YouTubeResponse>() {
            @Override
            public void onResponse(Call<YouTubeResponse> call, Response<YouTubeResponse> response) {
                if (response.isSuccessful()) {
                    YouTubeResponse youTubeResponse = response.body();
                    if (youTubeResponse != null) {
                        List<YouTubeVideo> liveVideos = youTubeResponse.getVideos();

                        Log.e("C","success");
                        if(videos!=null) {
                            videos.addAll(liveVideos);
                        }
                    }
                } else {
                    Log.e("VideoList", "Failed to get videos: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<YouTubeResponse> call, Throwable t) {
                Log.e("VideoList", "Error: " + t.getMessage());
            }
        });

        Call<YouTubeResponse> call2 = youTubeService.searchVideos(apiKey, CHANNEL_ID, "snippet", "viewCount", 50,"none","video");
        call2.enqueue(new Callback<YouTubeResponse>() {
            @Override
            public void onResponse(Call<YouTubeResponse> call, Response<YouTubeResponse> response) {
                if (response.isSuccessful()) {
                    YouTubeResponse youTubeResponse = response.body();
                    if (youTubeResponse != null) {
                        List<YouTubeVideo> noneliveVideos = youTubeResponse.getVideos();
                        //Log.println(Log.ASSERT,"hi","he");

                        Log.e("C","succ");
                        videos.addAll(noneliveVideos);
                        showVideos(videos);

                    }
                } else {
                    Log.e("VideoList", "Failed to get videos: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<YouTubeResponse> call, Throwable t) {
                Log.e("VideoList", "Error: " + t.getMessage());
            }
        });
    }

    private void showVideos(List<YouTubeVideo> videos) {
        progressDialog.dismiss();
        RecyclerView recyclerView = findViewById(R.id.recy);

        videoAdapter = new VideoAdapter(VideoList.this,videos);
        recyclerView.setLayoutManager(new GridLayoutManager(VideoList.this,1));

        recyclerView.setAdapter(videoAdapter);

        videoAdapter.notifyDataSetChanged();
    }

}
