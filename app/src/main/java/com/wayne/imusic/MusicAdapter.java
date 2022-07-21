package com.wayne.imusic;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private List<MusicListItem> musicListItems;
    private Context context;

    public MusicAdapter(List<MusicListItem> listItems, Context context){
        this.musicListItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View musicView = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_list_item, parent, false);

        return new ViewHolder(musicView);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder holder, int position) {
        MusicListItem musicListItem = musicListItems.get(position);

        new downloadImageTask(holder.musicImageView).execute(musicListItem.getImageUrl());

        holder.musicTextView.setText(musicListItem.getMusicName());
        holder.artistTextView.setText(musicListItem.getArtistName());

        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
        );

        holder.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer.isPlaying()){
                    audioPause(mediaPlayer);
                    holder.playBtn.setImageResource(android.R.drawable.ic_media_play);
                }
                else {
                    audioPlay(mediaPlayer, musicListItem.getMusicUrl());
                    holder.playBtn.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });
    }

    public void audioPlay(MediaPlayer mediaPlayer, String musicUrl){
        try {
            mediaPlayer.setDataSource(musicUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }
        catch (Exception e){
            System.out.println("media player exception: " + e);
        }
    }

    public void audioPause(MediaPlayer mediaPlayer){
        try {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        catch (Exception e){
            System.out.println("media player exception: " + e);
        }

    }

    @Override
    public int getItemCount() {
        return musicListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView musicImageView;
        public TextView musicTextView;
        public TextView artistTextView;
        public ImageView playBtn;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            musicImageView = (ImageView) itemView.findViewById(R.id.musicImageView);
            musicTextView = (TextView) itemView.findViewById(R.id.musicName);
            artistTextView = (TextView) itemView.findViewById(R.id.artistName);
            playBtn = (ImageView) itemView.findViewById(R.id.playButton);
        }
    }

    private class downloadImageTask extends AsyncTask<String, Void, Bitmap>{
        ImageView imgView;

        public downloadImageTask(ImageView image){
            this.imgView = image;
        }

        protected Bitmap doInBackground(String... url){
            String imgUrl = url[0];
            Bitmap img = null;
            try {
                InputStream in = new java.net.URL(imgUrl).openStream();
                img = BitmapFactory.decodeStream(in);
            }
            catch (Exception e){
                System.out.println("image loader exception: " + e);
            }
            return img;
        }

        protected void onPostExecute(Bitmap bitmap){
            imgView.setImageBitmap(bitmap);
        }
    }

}
