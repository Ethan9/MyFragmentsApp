package com.example.myfragmentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.example.myfragmentsapp.dummy.DummyContent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements FirstFragment.OnFragmentInteractionListener,
        GameFragment.OnFragmentInteractionListener,
        SecondFragment.OnListFragmentInteractionListener {

    private ArrayList<Leaderboard> leaderboards = new ArrayList<Leaderboard>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leaderboards.add(new Leaderboard("Bob",55f));
        leaderboards.add(new Leaderboard("Mary",70f));
        leaderboards.add(new Leaderboard("Jim",35f));

        setContentView(R.layout.activity_main);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    public ArrayList<Leaderboard> getLeaderboards() {
        return leaderboards;
    }

    public void setLeaderboards(ArrayList<Leaderboard> leaderboards) {
        this.leaderboards = leaderboards;
    }



}

