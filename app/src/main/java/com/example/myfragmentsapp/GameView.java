package com.example.myfragmentsapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.media.AudioManager;
import android.media.SoundPool;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;


import java.util.ArrayList;


public class GameView extends SurfaceView implements Runnable {
    SurfaceHolder myHolder;
    Thread myThread;
    boolean isRunning = true;
    ArrayList<GameObject> gameObjs = new ArrayList<GameObject>();
    Paint pWhite;
    Paint pTrue;
    Paint pFalse;
    Paint pQuestion;
    GameObject player;
    int trueWall;
    int falseWall;
    EndListener endListener;
    String[] questions = {"5 is the pH of a weak acid?", "A lemon is a strong acid?", "Bleach is a weak alkali?", "Distilled water is a neutral substance?", "An acid and an alkali reaction creates hydrogen?", "Potassium is a reactive metal?", "Acids can treat bee stings?", "Ca is the chemical symbol for calcium?", "The periodic table contains compounds?", "H is the chemical symbol for hydrogen?"};
    Boolean[] answers = {true, false, false, true, false, true, false, true, false, true };
    int questionNumber = 0;
    int score = 0;
    SoundPool sounds;
    int lose;
    int win;


    private float x, y, z;
    Paint p = new Paint();

    public GameView(Context context, EndListener endListener) {
        super(context);
        this.endListener = endListener;
        player = new GameObject(500, 1000, ContextCompat.getDrawable(context, R.drawable.scientist));
        gameObjs.add(player);
        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        lose = sounds.load(context, R.raw.lose, 1);
        win = sounds.load(context, R.raw.win, 1);
        pWhite = new Paint();
        pWhite.setColor(Color.WHITE);
        pTrue = new Paint();
        pTrue.setColor(Color.GREEN);
        pFalse = new Paint();
        pFalse.setColor(Color.RED);
        pQuestion = new Paint();
        pQuestion.setColor(Color.BLACK);
        pQuestion.setTextSize(50);
        p.setColor(Color.BLACK);
        p.setTextSize(100);
        trueWall = 300;
        falseWall = 800;
        myHolder = getHolder();
        myThread = new Thread(this);
        start();
    }


    @Override
    public void setX(float x) {
        player.setDx(-x);
    }


    @Override
    public void run() {
        while (isRunning) {
            if (!myHolder.getSurface().isValid())
                continue;
            Canvas canvas = myHolder.lockCanvas();
            canvas.drawRect(0, 0, 2000, 3000, pWhite);
            canvas.drawRect(0, 0, trueWall, 3000, pTrue);
            canvas.drawRect(falseWall, 0, 1500, 3000, pFalse);
            canvas.drawText("Score: " + score,300, 100, p);
            canvas.drawText("False" ,800, 820, p);
            canvas.drawText("True" ,0, 800, p);
            canvas.drawRect(0, 1800, 10000, 20000, pWhite);
            canvas.drawText(questions[questionNumber], 0, 1900, pQuestion);

            for (GameObject gameObject : gameObjs) {
                gameObject.move(canvas);
            }
            checkCollision();

            myHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void checkCollision() {
        if (player.getX() < trueWall && answers[questionNumber]) {
            sounds.play(win, 1.0f, 1.0f, 0, 0, 1.5f);
            addScore();
        } else if (player.getX() > falseWall && !answers[questionNumber]) {
            sounds.play(win, 1.0f, 1.0f, 0, 0, 1.5f);
            addScore();
        } else if (player.getX() < trueWall && !answers[questionNumber]) {
            sounds.play(lose, 1.0f, 1.0f, 0, 0, 1.5f);
            takeScore();
        } else if (player.getX() > falseWall && answers[questionNumber]) {
            sounds.play(lose, 1.0f, 1.0f, 0, 0, 1.5f);
            takeScore();
        }
    }


    void resetCharacter() {
        player.setX(500);
    }


    void addScore() {
        score = score + 50;
        questionNumber++;
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (questionNumber == questions.length) {
                    endListener.onGameEnd(score);
                }
            }
        });
        resetCharacter();
    }

    void takeScore() {
        score = score - 50;
        questionNumber++;
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (questionNumber == questions.length) {
                    endListener.onGameEnd(score);
                }
            }
        });
        resetCharacter();
    }

    public interface EndListener {
        void onGameEnd(int score);
    }

    public void stop() {
        isRunning = false;
        while (true) {
            try {
                myThread.join();
                break;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // block until thread dies
            break;
        }
    }

    public void start() {
        isRunning = true;
        myThread = new Thread(this);
        myThread.start();
    }
}

