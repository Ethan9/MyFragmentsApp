package com.example.myfragmentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.myfragmentsapp.dummy.DummyContent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements MenuFragment.OnFragmentInteractionListener,
        GameFragment.OnFragmentInteractionListener,
        AddScoreFragment.OnFragmentInteractionListener,
        WelcomeFragment.OnFragmentInteractionListener,
        LeaderboardFragment.OnListFragmentInteractionListener {

    private ArrayList<Player> players = new ArrayList<Player>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set fullscreen
        players.add(new Player("Bob",55f));
        players.add(new Player("Mary",70f));
        players.add(new Player("Jim",35f));
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addScore(String name, Float score) {
        players.add(new Player(name, score));
    }

    public void saveToFile(String fileContents, String fileName) {
        Context context = getApplicationContext();
        Log.d("ID","file dir = " + context.getFilesDir());
        try {
            File fp = new File(context.getFilesDir(), fileName);
            FileWriter out = new FileWriter(fp);
            out.write(fileContents);
            out.close();
        } catch (IOException e) {
            Log.d("Me","file error:" + e);
        }
    }





}

