package ru.kirillisachenko.virusgame.gamecontrollers;

import android.app.usage.UsageEvents;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.metrics.Event;
import android.text.method.Touch;
import android.util.Log;

import ru.kirillisachenko.virusgame.gameobjects.Hero;

public class Joystick {
    private float xB, yB, iRad, oRad, xM, yM, actuatorX, actuatorY;
    private boolean isPressed = false;
    Paint paint1, paint2;
    public Joystick(float xB, float yB, float iRad, float oRad){
        this.xB = xB;
        this.yB = yB;
        this. iRad = iRad;
        this.oRad = oRad;
        this.xM = xB;
        this.yM = yB;
        paint1 = new Paint();
        paint2 = new Paint();
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint1.setColor(Color.GRAY);
        paint2.setColor(Color.BLACK);
    }

    public  void draw(Canvas canvas){
        canvas.drawCircle(xB, yB, oRad , paint1);
        canvas.drawCircle(xM, yM, iRad, paint2);
        Log.d("RRR", "RISUU JOYSTICK" );
    }

    public void update(){
        xM = xB + actuatorX * oRad;
        yM = yB + actuatorY * oRad;
    }


    public boolean isInJoystick(float x, float y ){
        float distance = (float) Math.sqrt(Math.pow((xB - x), 2) +Math.pow((y - yB), 2));
        return distance <= oRad;
    }

    public void setActuator(float xA, float yA){
        float deltaX = xA - xB;
        float deltaY = yA - yB;
        float delta = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        if (delta < oRad){
            actuatorX = deltaX / oRad;
            actuatorY = deltaY / oRad;
        } else{
            actuatorX = deltaX/ delta;
            actuatorY = deltaY/ delta;
        }
    }

    public float getActuatorX() {
        return actuatorX;
    }

    public float getActuatorY() {
        return actuatorY;
    }



    public void resetActuator() {
        actuatorX = 0;
        actuatorY = 0;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }
}
