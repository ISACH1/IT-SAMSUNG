package ru.kirillisachenko.virusgame.gameobjects;

import android.content.Context;
import android.graphics.Canvas;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;

public abstract class GameObject {
    private  float xPosition;
    private float yPosition;


    public GameObject(float  xPosition, float yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public abstract void update();





    public abstract void draw(Canvas canvas);

    public float getxPosition() {
        return xPosition;
    }

    public abstract void draw(Canvas canvas, GameDisplay gameDisplay);

    public float getyPosition() {
        return yPosition;
    }
}
