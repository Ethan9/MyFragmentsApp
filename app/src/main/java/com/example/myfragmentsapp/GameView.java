package com.example.myfragmentsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;


public class GameView extends SurfaceView implements Runnable {
    SurfaceHolder myHolder;
    Thread myThread;
    boolean isRunning=true;
    ArrayList<GameObject> gameObjs = new ArrayList<GameObject>();
    Paint pWhite;

    private float x,y,z;
    Paint p = new Paint();
    public GameView(Context context) {
        super(context);
        pWhite = new Paint();
        pWhite.setColor(Color.WHITE);
        myHolder = getHolder();
        myThread = new Thread(this);
        start();
    }


    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void setZ(float z) {
        this.z = z;
    }


    @Override
    public void run() {
        while(isRunning)
        {
            if(!myHolder.getSurface().isValid())
                continue;
            Canvas canvas = myHolder.lockCanvas();
            int score = 0;
            p.setColor(Color.RED);
            p.setTextSize(50);

            if(x < 0) {
                canvas.drawText("True", 300+50*x ,600+50*y,  p);
            }
            else {
                canvas.drawText("False", 300+50*x ,600+50*y,  p);
            }


            myHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void stop()
    {
        isRunning=false;
        while(true)
        {
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

    public void start()
    {
        isRunning=true;
        myThread = new Thread(this);
        myThread.start();
    }
}

