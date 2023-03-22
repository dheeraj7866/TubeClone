package com.example.tubeclone.homeAdapter;

public class Channel {
    String name;
    String channelId;
    int logo;

    public Channel(String name, String channelId, int logo) {
        this.name = name;
        this.channelId = channelId;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
