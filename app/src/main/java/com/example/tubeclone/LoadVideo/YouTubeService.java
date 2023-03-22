package com.example.tubeclone.LoadVideo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YouTubeService {

    @GET("search")
    Call<YouTubeResponse> searchVideos(
            @Query("key") String apiKey,
            @Query("channelId") String channelId,
            @Query("part") String part,
            @Query("order") String order,
            @Query("maxResults") int maxResults,
            @Query("eventType") String event,
            @Query("type") String videoType
    );

}

