package com.example.tubeclone.LoadVideo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubeclone.R;
import com.example.tubeclone.VideoPlayer;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<YouTubeVideo> videos;
    Context context;

    public VideoAdapter(Context context,List<YouTubeVideo> videos) {
        this.videos = videos;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(videos.get(position).getVideoSnippet().getTitle());
        holder.description.setText(videos.get(position).getVideoSnippet().getDescription());
        Picasso.get().load(videos.get(position).getVideoSnippet().getThumbnails().getMedium().getUrl()).into(holder.thumbnail);
        String val=videos.get(position).getVideoSnippet().getLiveBroadcastContent();
        if(val.equals("live")){
            holder.isLive.setVisibility(View.VISIBLE);
        }else{
            holder.isLive.setVisibility(View.INVISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + videos.get(position).getVideoId()));
                Intent intent = new Intent(context, VideoPlayer.class);
                intent.putExtra("VIDEO_ID",videos.get(position).getVideoId().getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView title;
        TextView description, isLive;

        public ViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            isLive=itemView.findViewById(R.id.isLive);
        }
    }
}
