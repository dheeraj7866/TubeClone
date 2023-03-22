package com.example.tubeclone.homeAdapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import com.example.tubeclone.VideoList;


import java.util.ArrayList;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.viewHolder> {
    Context context;
    ArrayList<Channel> channels;


    public ChannelAdapter( ArrayList<Channel> channels, Context context) {
        this.channels = channels;

        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        View view= LayoutInflater.from(context).inflate(R.layout.grid_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.logoImage.setImageResource(channels.get(position).getLogo());
        holder.channel_name.setText(channels.get(position).getName());

        holder.logoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = channels.get(position).getChannelId();
                Intent intent = new Intent(context, VideoList.class);
                intent.putExtra("url",url);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView logoImage;
        TextView channel_name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            logoImage=itemView.findViewById(R.id.logo_image);
            channel_name=itemView.findViewById(R.id.channelName);
        }
    }
}
