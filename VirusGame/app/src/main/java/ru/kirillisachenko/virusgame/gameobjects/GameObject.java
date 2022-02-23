package ru.kirillisachenko.virusgame.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public abstract class GameObject {
    protected   float xPosition;
    protected float yPosition;
    protected int healthPoint;
    protected int maxHealthPoint;



    public GameObject(float  xPosition, float yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public abstract void update();



    public abstract Bullet attack();
    public abstract Bullet attack(ArrayList<GameObject> enemies);


    public float getxPosition() {
        return xPosition ;
    }

    public abstract boolean canAttack();

    public abstract void draw(Canvas canvas, GameDisplay gameDisplay);

    public float getyPosition() {
        return yPosition ;
    }

    public abstract int getSize();

    public int getHealthPoint(){
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        if ( healthPoint > 0) {
            this.healthPoint = healthPoint;
            return;
        }
        this.healthPoint = 0;
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setMaxHealthPoint(int maxHealthPoint) {
        if (maxHealthPoint > 0) {
            this.maxHealthPoint = maxHealthPoint;
            return;
        }
        this.maxHealthPoint = 0;
    }
}
