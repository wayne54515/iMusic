package com.wayne.imusic;

public class MusicListItem {
    private String musicName;
    private String musicUrl;

    public MusicListItem(String name, String url){
        this.musicName = name;
        this.musicUrl = url;

    }

    public String getMusicName(){
        return musicName;
    }

    public String getMusicUrl(){
        return musicUrl;
    }
}
