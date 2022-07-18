package com.wayne.imusic;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.musicTextView.setText(musicListItem.getMusicName());
    }

    @Override
    public int getItemCount() {
        return musicListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView musicTextView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            musicTextView = (TextView) itemView.findViewById(R.id.musicName);
        }
    }

}
