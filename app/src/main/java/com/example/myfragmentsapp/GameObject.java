package com.example.myfragmentsapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.SensorManager;

public class GameObject {
    protected float x, y, dx, dy;
    protected Drawable image;

    Paint p = new Paint();



    public GameObject(float x, float y, Drawable image) {
        this.x = x;
        this.y = y;
        dx = 0;
        dy = 0;
        this.image = image;
        p.setColor(Color.RED);
        p.setTextSize(100);
    }

    void move(Canvas canvas)
    {
        x+=dx;
        y+=dy;
        if(x>canvas.getWidth() || x<0)
            dx=-dx;
        if(y>canvas.getHeight() || y<0)
            dy=-dy;
        //canvas.drawText("Hello", x, y, p);

        image.setBounds((int)x,(int)y,(int)(x+200f),(int)(y+200f));
        image.draw(canvas);
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

}
