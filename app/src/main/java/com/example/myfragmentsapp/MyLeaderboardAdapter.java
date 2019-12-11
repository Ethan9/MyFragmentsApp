package com.example.myfragmentsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MyLeaderboardAdapter extends RecyclerView.Adapter {
    ArrayList<Player> players;

    public MyLeaderboardAdapter(ArrayList<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_fragment_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder vh=(MyViewHolder)holder;
        vh.myItemTextView.setText(
                players.get(position).getName()
                        +","+ players.get(position).getScore()
        );

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView myItemTextView;
        public MyViewHolder(View v) {
            super(v);
            myItemTextView = (TextView)v.findViewById(R.id.myItemTextView);
        }
    }

}