package ru.kirillisachenko.rogulikegame;

import android.content.Context;
import android.graphics.Canvas;

public class Hero {
    Context context;
    int speed;
    float xSpeed;
    float ySpeed;
    float x;
    float y;
    Weapon weapon1, weapon2;
    public Hero(Context context,float  x, float y, Weapon startWeapon  ){
        this.x = x;
        this.y = y;
        this.weapon1 = startWeapon;
    }


    public void update(){
        x += xSpeed;
        y += ySpeed;
    }

    public void draw(Canvas canvas){

    }



}
