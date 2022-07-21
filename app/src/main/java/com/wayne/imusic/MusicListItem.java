package com.wayne.imusic;

public class MusicListItem {
    private String musicName;
    private String artistName;
    private String musicUrl;
    private String imageUrl;

    public MusicListItem(String musicName, String artistName, String musicUrl, String imgUrl){
        this.musicName = musicName;
        this.artistName = artistName;
        this.musicUrl = musicUrl;
        this.imageUrl = imgUrl;

    }

    public String getMusicName(){
        return musicName;
    }

    public String getArtistName(){
        return artistName;
    }

    public String getMusicUrl(){
        return musicUrl;
    }

    public String getImageUrl(){
        return imageUrl;
    }



}
